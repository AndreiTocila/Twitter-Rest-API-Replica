package ro.uaic.info.fiipractic.DTO;

import lombok.*;
import ro.uaic.info.fiipractic.entity.UserEntity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostDTO {
    private Integer id;

    private String message;

    private Timestamp timestamp;

    private UserDTO user;

    private Set<UserDTO> mentions = new HashSet<>();
    private Set<UserDTO> likes = new HashSet<>();
    private Set<ReplyDTO> replies = new HashSet<>();
}
