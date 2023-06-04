package ro.uaic.info.fiipractic.DTO;

import lombok.*;

import javax.persistence.Basic;
import javax.persistence.Column;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String mail;
    //private List<UserDTO> followers;
}
