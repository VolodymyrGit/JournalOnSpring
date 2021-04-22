package volm.journal.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;
import volm.journal.repo.UserRepo;
import volm.journal.security.Role;
import volm.journal.service.UserService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(RegistrationDto registrationDto) {

        List<User> users = userRepo.findAll();

        User userFromDto = RegistrationDto.turnIntoUser(registrationDto);

        if (users.isEmpty()) {

            userFromDto.setRoles(Collections.singletonList(Role.ADMIN));
        }
        userFromDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        return userRepo.save(userFromDto);
    }


    @Override
    public User changeUserInfo(ChangeUserInfoDto changeUserInfoDto, User currentUser) {

        currentUser.setUserName(changeUserInfoDto.getName());

        currentUser.setEmail(changeUserInfoDto.getEmail());

        currentUser.setPhoneNumber(changeUserInfoDto.getPhone());

        return userRepo.save(currentUser);
    }


    @Override
    public boolean checkIfEmailAlreadyExist(String newEmail) {

        List<String> emails = userRepo.findAll().stream()
                .map(User::getEmail)
                .collect(Collectors.toList());

        return emails.contains(newEmail);
    }


    @Override
    public User saveEncodedPassword(User currentUser, String password) {

        currentUser.setPassword(passwordEncoder.encode(password));
        userRepo.save(currentUser);

        return currentUser;
    }
}