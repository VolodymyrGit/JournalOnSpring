package volm.journal.service;

import org.springframework.stereotype.Service;
import volm.journal.dto.ChangeUserPasswordDto;
import volm.journal.dto.ChangeUserNameEmailPhoneDto;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;


@Service
public interface UserService {


//    boolean authorized(String email, String password);

    User registerUser(RegistrationDto registrationDto);

    User changeUserNameEmailPhone(ChangeUserNameEmailPhoneDto nameEmailPhoneDto, User currentUser);

    boolean checkIfEmailNotExist(String newEmail);

    User saveEncodedPassword(User currentUser, String password);

    boolean compareUserPassWithEnteredCurrentPass(String currentPasswordFromForm, User currentUser);
}
