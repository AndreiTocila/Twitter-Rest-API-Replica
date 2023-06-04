package ro.uaic.info.fiipractic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.uaic.info.fiipractic.entity.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    List<UserEntity> findByFirstName(String firstName);

    List<UserEntity> findByLastName(String lastName);

    List<UserEntity> findByFollowers(UserEntity userEntity);
}
