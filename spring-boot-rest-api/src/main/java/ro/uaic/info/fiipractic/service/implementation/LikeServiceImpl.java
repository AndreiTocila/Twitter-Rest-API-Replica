package ro.uaic.info.fiipractic.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uaic.info.fiipractic.entity.PostEntity;
import ro.uaic.info.fiipractic.entity.UserEntity;
import ro.uaic.info.fiipractic.exception.custom.PostNotFoundException;
import ro.uaic.info.fiipractic.exception.custom.UserNotFoundException;
import ro.uaic.info.fiipractic.repository.PostRepository;
import ro.uaic.info.fiipractic.repository.UserRepository;
import ro.uaic.info.fiipractic.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    @Autowired
    public LikeServiceImpl(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @Override
    public void likePost(Integer userId, Integer postId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found!"));

        post.getLikes().add(user);
        postRepository.save(post);
    }

    @Override
    public void deleteLike(Integer userId, Integer postId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found!"));

        if (post.getLikes().contains(user)) {
            post.getLikes().remove(user);
            postRepository.save(post);
        }
    }
}
