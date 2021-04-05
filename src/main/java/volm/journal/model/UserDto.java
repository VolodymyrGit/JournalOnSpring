package volm.journal.model;

import lombok.Builder;
import lombok.Data;
import volm.journal.enums.Role;


@Builder
@Data
public class UserDto {

    private String name;

    private String email;

    private String phone;

    private Group groupId;

    private Role role;

    private String password;
}

