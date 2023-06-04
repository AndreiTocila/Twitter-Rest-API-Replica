package ro.uaic.info.fiipractic.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uaic.info.fiipractic.DTO.PostDTO;
import ro.uaic.info.fiipractic.DTO.UserDTO;
import ro.uaic.info.fiipractic.DTO.builder.PostBuilder;
import ro.uaic.info.fiipractic.DTO.builder.UserBuilder;
import ro.uaic.info.fiipractic.entity.PostEntity;
import ro.uaic.info.fiipractic.entity.ReplyEntity;
import ro.uaic.info.fiipractic.entity.UserEntity;
import ro.uaic.info.fiipractic.exception.custom.*;
import ro.uaic.info.fiipractic.repository.PostRepository;
import ro.uaic.info.fiipractic.repository.UserRepository;
import ro.uaic.info.fiipractic.service.PostService;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void createPost(Integer user_id, PostDTO post) {
        UserEntity user = userRepository.findById(user_id).orElseThrow(() -> new UserNotFoundException("User not found!"));

        PostEntity postEntity = PostEntity.builder()
                .message(post.getMessage())
                .user(user)
                .mentions(removeMention(post.getMentions()).stream().map(UserBuilder::dtoToEntity).collect(Collectors.toSet()))
                .build();
        postRepository.save(postEntity);
    }

    @Override
    public void repost(Integer userId, Integer postId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found!"));

        if (post.getUser().equals(user))
            throw new PostAlreadyYoursException("This post is yours. Can not repost this.");

        PostEntity repost = PostEntity.builder()
                .message(post.getMessage())
                .user(user)
                .build();

        postRepository.save(repost);
    }

    @Override
    public List<PostDTO> getPosts(Integer userId, Optional<String> timestamp) {
        List<PostEntity> posts;

        if (timestamp.isPresent())
            posts = postRepository.findByUserIdAndTimestampGreaterThanOrderByTimestamp(userId, Timestamp.valueOf(timestamp.get()));
        else
            posts = postRepository.findByUserId(userId);

        if (posts.isEmpty())
            throw new PostNotFoundException("No posts found!");

        return posts.stream().map(PostBuilder::entityToDto).toList();
    }

    @Override
    public List<PostDTO> getFeed(Integer userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<UserEntity> following = userRepository.findByFollowers(user);
        List<PostEntity> followersPosts = postRepository.findByUserIdIn(following.stream().map(UserEntity::getId).toList());

        if (followersPosts.isEmpty())
            throw new EmptyFeedException("No posts found in your feed! Follow some users to see their posts!");

        for (PostEntity post : followersPosts) {
            post.setReplies(removeReply(post.getReplies()));
        }

        return followersPosts.stream().map(PostBuilder::entityToDto).toList();
    }

    @Override
    public List<PostDTO> getMentions(Integer userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));

        List<PostEntity> mentionPosts = postRepository.findByMentions(user);

        if (mentionPosts.isEmpty())
            throw new PostNotFoundException("No posts found!");

        for (PostEntity post : mentionPosts) {
            post.setReplies(removeReply(post.getReplies()));
        }

        return mentionPosts.stream().map(PostBuilder::entityToDto).toList();
    }


    @Override
    public void deletePost(Integer userId, Integer postId) {
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found!"));
        if (!post.getUser().getId().equals(userId)) {
            throw new CanNotDeletePostException("You can't delete this post!");
        }
        postRepository.delete(post);
    }

    private Set<ReplyEntity> removeReply(Set<ReplyEntity> replies) {
        Set<ReplyEntity> result = new HashSet<>();
        for (ReplyEntity reply : replies) {
            if (reply.getPublicPost()) {
                result.add(reply);
            }
        }
        return result;
    }

    private Set<UserDTO> removeMention(Set<UserDTO> mentions) {
        Set<UserDTO> result = new HashSet<>();
        for (UserDTO mention : mentions) {
            if (userRepository.findById(mention.getId()).isPresent())
                result.add(mention);
        }
        return result;
    }
}
