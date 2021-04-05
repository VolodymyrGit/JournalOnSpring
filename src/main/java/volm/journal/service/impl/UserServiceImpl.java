package volm.journal.service.impl;


import org.springframework.stereotype.Service;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;
import volm.journal.util.SecurityUtil;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;


    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public boolean authorized(String email, String password) {

        Optional<User> userFromDB = userRepo.findByEmailEquals(email);

        if(userFromDB.isPresent()) {

            User user = userFromDB.get();
            String securePassword = SecurityUtil.getSecurePassword(password, user.getSalt());

            return user.getPassword().equals(securePassword);
        }
        return false;
    }
}