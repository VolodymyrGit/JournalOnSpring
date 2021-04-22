package volm.journal.util;

import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.model.User;

public class ValidationUtil {


    public static boolean validateEmailByRegex(String email) {

        return email.matches("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\\b");
    }


    public static boolean checkWhetherParamsNotNull(ChangeUserInfoDto dto, User currentUser) {

        return currentUser.getId() == dto.getId() && dto.getName() != null && dto.getEmail() != null
                && dto.getPhone() != null;
    }
}
