package ro.uaic.info.fiipractic.DTO.builder;

import ro.uaic.info.fiipractic.DTO.UserDTO;
import ro.uaic.info.fiipractic.DTO.UserRegisterDTO;
import ro.uaic.info.fiipractic.entity.UserEntity;

import java.util.stream.Collectors;


public class UserBuilder {

    public static UserDTO entityToDto(UserEntity userEntity) {

        return UserDTO.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .mail(userEntity.getMail())
                //.followers(userEntity.getFollowers().stream().map(UserBuilder::entityToDto).collect(Collectors.toList()))
                .build();
    }

    public static UserEntity dtoToEntity(UserDTO userDTO) {
        return UserEntity.builder()
                .id(userDTO.getId())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .mail(userDTO.getMail())
                .build();
    }

    public static UserEntity dtoRegisterToEntity(UserRegisterDTO userRegisterDTO) {
        return UserEntity.builder()
                .firstName(userRegisterDTO.getFirstName())
                .lastName(userRegisterDTO.getLastName())
                .mail(userRegisterDTO.getMail())
                .password(userRegisterDTO.getPassword())
                .build();
    }
}
