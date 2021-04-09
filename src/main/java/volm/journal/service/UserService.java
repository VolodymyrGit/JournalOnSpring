package volm.journal.service;

import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;
import volm.journal.dto.ChangeInfoDto;

public interface UserService {

    boolean authorized(String email, String password);

    User registration(RegistrationDto registrationDto);

    User updateUserInfo(ChangeInfoDto changeInfoDto, User currentUser);
}