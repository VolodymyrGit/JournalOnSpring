package volm.journal.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import volm.journal.dto.ChangeUserPasswordDto;
import volm.journal.dto.ChangeUserNameEmailPhoneDto;
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
    public User changeUserNameEmailPhone(ChangeUserNameEmailPhoneDto nameEmailPhoneDto, User currentUser) {

            currentUser.setUserName(nameEmailPhoneDto.getName());

        currentUser.setEmail(nameEmailPhoneDto.getEmail());

        currentUser.setPhoneNumber(nameEmailPhoneDto.getPhone());

        return userRepo.save(currentUser);
    }


    @Override
    public boolean checkIfEmailNotExist(String newEmail) {

            List<String> emails = userRepo.findAll().stream()
                    .map(User::getEmail)
                    .collect(Collectors.toList());

        return !emails.contains(newEmail);
    }



    @Override
    public boolean compareUserPassWithEnteredCurrentPass(String currentPasswordFromForm, User currentUser) {

        return passwordEncoder.matches(currentPasswordFromForm, currentUser.getPassword());
    }


    @Override
    public User saveEncodedPassword(User currentUser, String password) {

        String encodedPassword = passwordEncoder.encode(password);
        currentUser.setPassword(encodedPassword);
        userRepo.save(currentUser);

        return currentUser;
    }
}