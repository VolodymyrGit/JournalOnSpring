package volm.journal.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import volm.journal.enums.Role;


@AllArgsConstructor
@Builder
@Data
public class ChangeInfoDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Long groupId;

    private Role role;

    private String password;

    private String npassword;
}