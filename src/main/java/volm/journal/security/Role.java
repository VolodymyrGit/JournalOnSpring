package volm.journal.security;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    TEACHER,
    STUDENT,
    ADMIN;


    @Override
    public String getAuthority() {
        return name();
    }
}