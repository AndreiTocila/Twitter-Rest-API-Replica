package ro.uaic.info.fiipractic.service;

public interface LikeService {

    void likePost(Integer userId, Integer postId);

    void deleteLike(Integer userId, Integer postId);
}
