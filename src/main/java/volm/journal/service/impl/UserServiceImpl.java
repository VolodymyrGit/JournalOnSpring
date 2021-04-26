package volm.journal.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volm.journal.dto.RegistrationDto;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.dto.ChangeInfoDto;
import volm.journal.repo.GroupRepo;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;
import volm.journal.util.SecurityUtil;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final GroupRepo groupRepo;
    private final LessonRepo lessonRepo;
    private final HomeworkRepo homeworkRepo;


    @Override
    public boolean authorized(String email, String password) {

        Optional<User> userFromDB = userRepo.findByEmailEquals(email);

        if (userFromDB.isPresent()) {

            User user = userFromDB.get();
            String securePassword = SecurityUtil.getSecurePassword(password, user.getSalt());

            return user.getPassword().equals(securePassword);
        }
        return false;
    }


    @Override
    public User registration(RegistrationDto registrationDto) {

        Group group = groupRepo.findById(registrationDto.getGroupId())
                .orElseThrow(() -> new NoSuchElementException());

        String salt = SecurityUtil.generateRandomSalt();
        String hashedPassword = SecurityUtil.getSecurePassword(registrationDto.getPassword(), salt);

        User user = new User(registrationDto.getName(),
                registrationDto.getEmail(),
                registrationDto.getPhone(),
                hashedPassword,
                salt,
                group,
                registrationDto.getRole());

        List<Lesson> lessons = lessonRepo.findAllByGroupEquals(group);

        User savedUser = userRepo.save(user);

        if(lessons.isEmpty()) {

            Lesson newLesson = lessonRepo.save(new Lesson(group, new Date()));

            if(user.getRole().equals(Role.STUDENT)) {
                homeworkRepo.save(new Homework(newLesson, user));
            }
        }

        if (registrationDto.getRole().name().equals("STUDENT")) {

            lessons.forEach(l -> homeworkRepo.save(new Homework(l, savedUser)));
        }
        return savedUser;
    }


    @Override
    public User updateUserInfo(ChangeInfoDto changeInfoDto, User currentUser) {

        Group group = groupRepo.findById(changeInfoDto.getGroupId())
                .orElseThrow(() -> new NoSuchElementException());

        String salt = SecurityUtil.generateRandomSalt();
        String newHashedPassword = SecurityUtil.getSecurePassword(changeInfoDto.getNpassword(), salt);

        User changedUser = new User(changeInfoDto.getId(),
                changeInfoDto.getName(),
                changeInfoDto.getEmail(),
                changeInfoDto.getPhone(),
                newHashedPassword,
                salt,
                group,
                changeInfoDto.getRole());

        String hashedPassword = SecurityUtil.getSecurePassword(changeInfoDto.getPassword(), currentUser.getSalt());

        if (!currentUser.getPassword().equals(hashedPassword)) {

            return userRepo.save(changedUser);
        }
        return currentUser;
    }
}