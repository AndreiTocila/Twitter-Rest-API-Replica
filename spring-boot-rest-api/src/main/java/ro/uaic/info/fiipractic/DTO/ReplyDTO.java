package ro.uaic.info.fiipractic.DTO;

import lombok.*;
import org.apache.catalina.User;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReplyDTO {

    private Integer id;
    private Boolean publicPost;
    private PostDTO post;

}
