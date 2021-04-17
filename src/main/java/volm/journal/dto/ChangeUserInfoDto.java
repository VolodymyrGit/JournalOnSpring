package volm.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import volm.journal.security.Role;
import volm.journal.model.Group;
import volm.journal.model.User;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class ChangeUserInfoDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String password;

    private String npassword;
}