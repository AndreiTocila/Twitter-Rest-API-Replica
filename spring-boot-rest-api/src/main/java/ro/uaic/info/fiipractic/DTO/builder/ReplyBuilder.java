package ro.uaic.info.fiipractic.DTO.builder;

import ro.uaic.info.fiipractic.DTO.ReplyDTO;
import ro.uaic.info.fiipractic.entity.ReplyEntity;

public class ReplyBuilder {

    public static ReplyDTO entityToDto(ReplyEntity replyEntity) {
        return ReplyDTO.builder()
                .id(replyEntity.getId())
                .post(PostBuilder.entityToDto(replyEntity.getPost()))
                .publicPost(replyEntity.getPublicPost())
                .build();
    }

    public static ReplyEntity dtoToEntity(ReplyDTO replyDTO) {
        return ReplyEntity.builder()
                .id(replyDTO.getId())
                .post(PostBuilder.dtoToEntity(replyDTO.getPost()))
                .publicPost(replyDTO.getPublicPost())
                .build();
    }
}
