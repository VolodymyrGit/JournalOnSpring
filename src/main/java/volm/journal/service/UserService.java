package volm.journal.service;

import org.springframework.stereotype.Service;
import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;


@Service
public interface UserService {


    User registerUser(RegistrationDto registrationDto);

    void validateEmailBySendingMessage(User user);

    User changeUserInfo(ChangeUserInfoDto nameEmailPhoneDto, User currentUser);

    boolean checkIfEmailAlreadyExist(String newEmail);

    User saveEncodedPassword(User currentUser, String password);
}
