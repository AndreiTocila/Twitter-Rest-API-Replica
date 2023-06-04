package ro.uaic.info.fiipractic.service;

import ro.uaic.info.fiipractic.DTO.UserDTO;
import ro.uaic.info.fiipractic.DTO.UserRegisterDTO;
import ro.uaic.info.fiipractic.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {

    void register(UserRegisterDTO user);

    List<UserDTO> findByCriteria(Optional<Integer> id, Optional<String> firstName, Optional<String> lastName);

    void follow(Integer id1, Integer id2);

    void unfollow(Integer id1, Integer id2);

    void delete(Integer id);
}
