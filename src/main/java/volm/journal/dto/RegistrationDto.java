package volm.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import volm.journal.model.User;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class RegistrationDto {

    private String name;

    private String email;

    private String phone;

    private String password;


    public static User turnIntoUser(RegistrationDto dto) {

        return new User(dto.name,
                dto.email,
                dto.phone,
                dto.password);
    }
}