package volm.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import volm.journal.security.Role;
import volm.journal.model.Group;
import volm.journal.model.User;
import volm.journal.util.SecurityUtil;

import java.util.List;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class RegistrationDto {

    private String name;

    private String email;

    private String phone;

//    private Long groupId;

//    private List<Role> roles;

    private String password;


    public static User turnIntoUser(RegistrationDto dto) {

//        String salt = SecurityUtil.generateRandomSalt();
//
//        String hashedPassword = SecurityUtil.getSecurePassword(dto.password, salt);

        return new User(dto.name,
                dto.email,
                dto.phone,
                dto.password);
    }
}