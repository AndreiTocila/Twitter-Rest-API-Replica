package ro.uaic.info.fiipractic.DTO.builder;

import lombok.Builder;
import ro.uaic.info.fiipractic.DTO.PostDTO;
import ro.uaic.info.fiipractic.entity.PostEntity;

import java.util.stream.Collectors;


public class PostBuilder {

    public static PostDTO entityToDto(PostEntity postEntity) {
        return PostDTO.builder()
                .id(postEntity.getId())
                .message(postEntity.getMessage())
                .timestamp(postEntity.getTimestamp())
                .user(UserBuilder.entityToDto(postEntity.getUser()))
                .mentions(postEntity.getMentions().stream().map(UserBuilder::entityToDto).collect(Collectors.toSet()))
                .likes(postEntity.getLikes().stream().map(UserBuilder::entityToDto).collect(Collectors.toSet()))
                .replies(postEntity.getReplies().stream().map(ReplyBuilder::entityToDto).collect(Collectors.toSet()))
                .build();
    }

    public static PostEntity dtoToEntity(PostDTO postDTO) {
        return PostEntity.builder()
                .id(postDTO.getId())
                .message(postDTO.getMessage())
                .timestamp(postDTO.getTimestamp())
                .user(UserBuilder.dtoToEntity(postDTO.getUser()))
                .mentions(postDTO.getMentions().stream().map(UserBuilder::dtoToEntity).collect(Collectors.toSet()))
                .likes(postDTO.getLikes().stream().map(UserBuilder::dtoToEntity).collect(Collectors.toSet()))
                .replies(postDTO.getReplies().stream().map(ReplyBuilder::dtoToEntity).collect(Collectors.toSet()))
                .build();
    }
}
