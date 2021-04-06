package volm.journal.service;

import volm.journal.model.User;
import volm.journal.model.UserDto;

public interface UserService {

    boolean authorized(String email, String password);

    User registration(UserDto userDto);

    User updateUserInfo(UserDto userDto, User currentUser);
}
