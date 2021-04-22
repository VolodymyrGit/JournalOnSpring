package volm.journal.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
@AllArgsConstructor
@Builder
public class ChangeUserInfoDto {

    private Long id;

    private String name;

    private String email;

    private String phone;
}
