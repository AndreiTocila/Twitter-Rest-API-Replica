package ro.uaic.info.fiipractic.service;

import ro.uaic.info.fiipractic.DTO.PostDTO;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface PostService {

    void createPost(Integer user_id,PostDTO post);

    void repost(Integer userId, Integer postId);

    List<PostDTO> getPosts(Integer userId, Optional<String> timestamp);

    List<PostDTO> getFeed(Integer userId);

    List<PostDTO> getMentions(Integer userId);

    void deletePost(Integer userId, Integer postId);
}
