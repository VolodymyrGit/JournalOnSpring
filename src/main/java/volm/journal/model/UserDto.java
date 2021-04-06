package volm.journal.model;

import lombok.Builder;
import lombok.Data;
import volm.journal.enums.Role;


@Builder
@Data
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private Long groupId;

    private Role role;

    private String password;

    private String npassword;


    public UserDto(String name, String email, String phone, Long groupId, Role role, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.groupId = groupId;
        this.role = role;
        this.password = password;
    }

    public UserDto(Long id, String name, String email, String phone,
                   Long groupId, Role role, String password, String npassword) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.groupId = groupId;
        this.role = role;
        this.password = password;
        this.npassword = npassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Long getGroupId() {
        return groupId;
    }

    public Role getRole() {
        return role;
    }

    public String getPassword() {
        return password;
    }

    public Long getId() {
        return id;
    }

    public String getNpassword() {
        return npassword;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", groupId=" + groupId +
                ", role=" + role +
                ", password='" + password + '\'' +
                '}';
    }


    public static final class UserDtoBuilder {
        private Long id;
        private String name;
        private String email;
        private String phone;
        private Long groupId;
        private Role role;
        private String password;
        private String npassword;

        private UserDtoBuilder() {
        }

        public static UserDtoBuilder anUserDto() {
            return new UserDtoBuilder();
        }

        public UserDtoBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public UserDtoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserDtoBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserDtoBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserDtoBuilder groupId(Long groupId) {
            this.groupId = groupId;
            return this;
        }

        public UserDtoBuilder role(Role role) {
            this.role = role;
            return this;
        }

        public UserDtoBuilder password(String password) {
            this.password = password;
            return this;
        }

        public UserDtoBuilder npassword(String npassword) {
            this.npassword = npassword;
            return this;
        }

        public UserDto buildWithoutIdAndNpassword() {
            return new UserDto(name, email, phone, groupId, role, password);
        }

        public UserDto build() {
            return new UserDto(id, name, email, phone, groupId, role, password, npassword);
        }
    }
}

