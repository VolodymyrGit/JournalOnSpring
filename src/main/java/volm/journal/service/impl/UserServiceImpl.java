package volm.journal.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import volm.journal.dto.ChangeUserInfoDto;
import volm.journal.dto.RegistrationDto;
import volm.journal.model.User;
import volm.journal.repo.GroupRepo;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
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
    private final GroupRepo groupRepo;
    private final LessonRepo lessonRepo;
    private final HomeworkRepo homeworkRepo;
    private final PasswordEncoder passwordEncoder;


//    @Override
//    public boolean authorized(String email, String password) {
//
//        Optional<User> userFromDB = userRepo.findByEmailEquals(email);
//
//        if (userFromDB.isPresent()) {
//
//            User user = userFromDB.get();
//            String securePassword = SecurityUtil.getSecurePassword(password, user.getSalt());
//
//            return user.getPassword().equals(securePassword);
//        }
//        return false;
//    }


    @Override
    public User registerUser(RegistrationDto registrationDto) {

//        Group group = groupRepo.findById(registrationDto.getGroupId())
//                .orElseThrow(() -> new NoSuchElementException());

        List<User> users = userRepo.findAll();

        User userFromDto = RegistrationDto.turnIntoUser(registrationDto);

//        List<Lesson> lessons = lessonRepo.findAllByGroupEquals(group);

        if(users.isEmpty()) {

            userFromDto.setRoles(Collections.singletonList(Role.ADMIN));
        }
        userFromDto.setPassword(passwordEncoder.encode(registrationDto.getPassword()));

        User savedUser = userRepo.save(userFromDto);

//        if(lessons.isEmpty()) {
//
//            Lesson newLesson = lessonRepo.save(new Lesson(group, new Date()));
//
//            if(userFromDto.getRole().equals(Role.STUDENT)) {
//                homeworkRepo.save(new Homework(newLesson, userFromDto));
//            }
//        }
//        if (registrationDto.getRole().name().equals("STUDENT")) {
//
//            lessons.forEach(l -> homeworkRepo.save(new Homework(l, savedUser)));
//        }
        return savedUser;
    }


    @Override
    public User changeUserNameAndPhone(ChangeUserInfoDto changeUserInfoDto, User currentUser) {

        if(changeUserInfoDto.getName() != null) {
            currentUser.setUserName(changeUserInfoDto.getName());

            return userRepo.save(currentUser);
        }


        if(changeUserInfoDto.getPhone() != null) {
            currentUser.setPhoneNumber(changeUserInfoDto.getPhone());

            return userRepo.save(currentUser);
        }
        return userRepo.save(currentUser);
    }


    @Override
    public String changeUserEmail(ChangeUserInfoDto changeUserInfoDto, User currentUser) {

        if(changeUserInfoDto.getEmail() != null) {

            List<String> emails = userRepo.findAll().stream()
                    .map(User::getEmail)
                    .collect(Collectors.toList());

            if(!emails.contains(changeUserInfoDto.getEmail()) && !changeUserInfoDto.getEmail().equals("")) {

                currentUser.setEmail(changeUserInfoDto.getEmail());

                userRepo.save(currentUser);
            } else {

                return "User with this email is already registered";
            }
        }
        return "";
    }


    @Override
    public String changeUserPassword(ChangeUserInfoDto changeUserInfoDto, User currentUser) {

        if (changeUserInfoDto.getPassword() != null && changeUserInfoDto.getNpassword() != null) {

            String encryptedPassword = passwordEncoder.encode(changeUserInfoDto.getPassword());

            if (currentUser.getPassword().equals(encryptedPassword)) {

                String encryptedNewPassword = passwordEncoder.encode(changeUserInfoDto.getNpassword());
                currentUser.setPassword(encryptedNewPassword);

                userRepo.save(currentUser);
            } else {
                return "You entered wrong current password";
            }
        }
        return "";
    }
}