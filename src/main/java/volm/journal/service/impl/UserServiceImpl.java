package volm.journal.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import volm.journal.dto.RegistrationDto;
import volm.journal.security.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.GroupRepo;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;


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


//    @Override
//    public User updateUserInfo(ChangeInfoDto changeInfoDto, User currentUser) {
//
//        Group group = groupRepo.findById(changeInfoDto.getGroupId())
//                .orElseThrow(() -> new NoSuchElementException());
//
//        User changedUser = ChangeInfoDto.turnIntoUser(changeInfoDto, group);
//
//        String hashedPassword = SecurityUtil.getSecurePassword(changeInfoDto.getPassword(), currentUser.getSalt());
//
//        if (!currentUser.getPassword().equals(hashedPassword)) {
//
//            return userRepo.save(changedUser);
//        }
//        return currentUser;
//    }
}