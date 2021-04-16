package volm.journal.service;

import org.springframework.stereotype.Service;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;


@Service
public interface UserService {


//    boolean authorized(String email, String password);

    User registerUser(RegistrationDto registrationDto);

//    User updateUserInfo(ChangeInfoDto changeInfoDto, User currentUser);
}
