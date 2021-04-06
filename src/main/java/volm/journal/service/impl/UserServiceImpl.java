package volm.journal.service.impl;


import org.springframework.stereotype.Service;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.model.UserDto;
import volm.journal.repo.GroupRepo;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.service.UserService;
import volm.journal.util.SecurityUtil;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final GroupRepo groupRepo;
    private final LessonRepo lessonRepo;
    private final HomeworkRepo homeworkRepo;

    public UserServiceImpl(UserRepo userRepo, GroupRepo groupRepo, LessonRepo lessonRepo, HomeworkRepo homeworkRepo) {
        this.userRepo = userRepo;
        this.groupRepo = groupRepo;
        this.lessonRepo = lessonRepo;
        this.homeworkRepo = homeworkRepo;
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

    @Override
    public User registration(UserDto userDto) {

        Group group = groupRepo.findById(userDto.getGroupId())
                .orElseThrow(() -> new NoSuchElementException());

        String salt = SecurityUtil.generateRandomSalt();
        String hashedPassword = SecurityUtil.getSecurePassword(userDto.getPassword(), salt);

        User user = new User(userDto.getName(),
                userDto.getEmail(),
                userDto.getPhone(),
                hashedPassword,
                salt,
                group,
                userDto.getRole());

        List<Lesson> lessons = lessonRepo.findAllByGroupEquals(group);

        User savedUser = userRepo.save(user);

        if(!lessons.isEmpty() && userDto.getRole().name().equals("STUDENT")) {

            lessons.forEach(l -> homeworkRepo.save(new Homework(l, savedUser)));
        }
        return savedUser;
    }

    @Override
    public User updateUserInfo(UserDto userDto, User currentUser) {

        Group group = groupRepo.findById(userDto.getGroupId())
                .orElseThrow(() -> new NoSuchElementException());

        String salt = SecurityUtil.generateRandomSalt();
        String newHashedPassword = SecurityUtil.getSecurePassword(userDto.getNpassword(), salt);

        User changedUser = new User(userDto.getId(),
                userDto.getName(),
                userDto.getEmail(),
                userDto.getPhone(),
                newHashedPassword,
                salt,
                group,
                userDto.getRole());

        String hashedPassword = SecurityUtil.getSecurePassword(userDto.getPassword(), currentUser.getSalt());

        if (!currentUser.getPassword().equals(hashedPassword)) {

            return userRepo.save(changedUser);
        }
        return currentUser;
    }
}