package ro.uaic.info.fiipractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.uaic.info.fiipractic.entity.PostEntity;
import ro.uaic.info.fiipractic.entity.UserEntity;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    List<PostEntity> findByUserId(Integer userId);

    @Query("select p from PostEntity p where p.user.id = ?1 and p.timestamp > ?2")
    List<PostEntity> findByUserIdAndTimestampGreaterThanOrderByTimestamp(Integer user_id, Timestamp timestamp);

    List<PostEntity> findByUserIdIn(List<Integer> userIds);

    List<PostEntity> findByMentions(UserEntity userEntity);
}
