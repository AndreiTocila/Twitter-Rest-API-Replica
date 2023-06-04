package ro.uaic.info.fiipractic.DTO;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String mail;
    private String password;
}
