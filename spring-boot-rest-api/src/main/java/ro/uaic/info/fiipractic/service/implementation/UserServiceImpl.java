package ro.uaic.info.fiipractic.service.implementation;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.uaic.info.fiipractic.DTO.UserDTO;
import ro.uaic.info.fiipractic.DTO.UserRegisterDTO;
import ro.uaic.info.fiipractic.DTO.builder.UserBuilder;
import ro.uaic.info.fiipractic.entity.UserEntity;
import ro.uaic.info.fiipractic.exception.custom.UserDontFollowException;
import ro.uaic.info.fiipractic.exception.custom.UserNotFoundException;
import ro.uaic.info.fiipractic.repository.UserRepository;
import ro.uaic.info.fiipractic.service.UserService;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(UserRegisterDTO user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(UserBuilder.dtoRegisterToEntity(user));
    }

    @Override
    public List<UserDTO> findByCriteria(Optional<Integer> id, Optional<String> firstName, Optional<String> lastName) {
        List<UserEntity> usersFound = new LinkedList<>();

        id.flatMap(userRepository::findById).ifPresent(usersFound::add);
        firstName.ifPresent(s -> usersFound.addAll(userRepository.findByFirstName(s)));
        lastName.ifPresent(s -> usersFound.addAll(userRepository.findByLastName(s)));

        if (usersFound.isEmpty()) {
            throw new UserNotFoundException("No user found!");
        }

        return usersFound.stream().map(UserBuilder::entityToDto).toList();
    }

    @Override
    public void follow(Integer id1, Integer id2) {
        UserEntity user1 = userRepository.findById(id1).orElseThrow(() -> new UserNotFoundException("No user found!"));
        UserEntity user2 = userRepository.findById(id2).orElseThrow(() -> new UserNotFoundException("No user found!"));
        user2.getFollowers().add(user1);
        userRepository.save(user2);
    }

    @Override
    public void unfollow(Integer id1, Integer id2) {
        UserEntity user1 = userRepository.findById(id1).orElseThrow(() -> new UserNotFoundException("No user found!"));
        UserEntity user2 = userRepository.findById(id2).orElseThrow(() -> new UserNotFoundException("No user found!"));

        if (user2.getFollowers().contains(user1)) {
            user2.getFollowers().remove(user1);
            userRepository.save(user2);
        } else {
            throw new UserDontFollowException("You don't follow this user!");
        }
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}