package volm.journal.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;


@Service
public interface UserService {


//    boolean authorized(String email, String password);

    User registerUser(RegistrationDto registrationDto);

    User changeUserNameAndPhone(ChangeUserInfoDto changeUserInfoDto, User currentUser);

    String changeUserEmail(ChangeUserInfoDto changeUserInfoDto, User currentUser);

    String changeUserPassword(ChangeUserInfoDto changeUserInfoDto, User currentUser);
}
