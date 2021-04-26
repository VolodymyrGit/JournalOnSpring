package volm.journal.util;


import org.springframework.mail.SimpleMailMessage;
import volm.journal.model.User;

public class ValidationUtil {


    public static boolean validateEmailByRegex(String email) {

        return email.matches("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}");
    }
}
