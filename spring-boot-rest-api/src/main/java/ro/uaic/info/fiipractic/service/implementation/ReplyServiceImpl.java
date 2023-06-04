package ro.uaic.info.fiipractic.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.uaic.info.fiipractic.entity.PostEntity;
import ro.uaic.info.fiipractic.entity.ReplyEntity;
import ro.uaic.info.fiipractic.entity.UserEntity;
import ro.uaic.info.fiipractic.exception.custom.PostNotFoundException;
import ro.uaic.info.fiipractic.exception.custom.UserNotFoundException;
import ro.uaic.info.fiipractic.repository.PostRepository;
import ro.uaic.info.fiipractic.repository.ReplyRepository;
import ro.uaic.info.fiipractic.repository.UserRepository;
import ro.uaic.info.fiipractic.service.ReplyService;

import java.util.Map;

@Service
public class ReplyServiceImpl implements ReplyService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    @Autowired
    public ReplyServiceImpl(UserRepository userRepository, PostRepository postRepository, ReplyRepository replyRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.replyRepository = replyRepository;
    }

    @Override
    public void createReply(Integer userId, Integer postId, Map<String,String> content) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found!"));
        PostEntity post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post not found!"));

        PostEntity new_post = new PostEntity();
        new_post.setMessage(content.get("message"));
        new_post.setUser(user);
        new_post = postRepository.save(new_post);

        ReplyEntity reply = new ReplyEntity();
        reply.setPost(new_post);
        reply.setParent(post);
        reply.setPublicPost(Boolean.parseBoolean(content.get("publicPost")));
        reply = replyRepository.save(reply);

        post.getReplies().add(reply);
        postRepository.save(post);
    }
}
