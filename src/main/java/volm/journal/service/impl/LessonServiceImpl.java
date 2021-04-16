//package volm.journal.service.impl;
//
//import org.springframework.stereotype.Service;
//import volm.journal.security.Role;
//import volm.journal.model.Group;
//import volm.journal.model.Homework;
//import volm.journal.model.Lesson;
//import volm.journal.model.User;
//import volm.journal.repo.HomeworkRepo;
//import volm.journal.repo.LessonRepo;
//import volm.journal.repo.UserRepo;
//import volm.journal.service.LessonService;
//
//import java.util.Date;
//import java.util.List;
//
//
//@Service
//public class LessonServiceImpl implements LessonService {
//
//    private final LessonRepo lessonRepo;
//    private final UserRepo userRepo;
//    private final HomeworkRepo homeworkRepo;
//
//    public LessonServiceImpl(LessonRepo lessonRepo, UserRepo userRepo, HomeworkRepo homeworkRepo) {
//        this.lessonRepo = lessonRepo;
//        this.userRepo = userRepo;
//        this.homeworkRepo = homeworkRepo;
//    }
//
//
//    @Override
//    public void addLesson(User user) {
//
//        Group group = user.getGroup();
//
//        Lesson savedLesson = lessonRepo.save(new Lesson(group, new Date()));
//
//        List<User> students = userRepo.findAllByGroupEqualsAndRoleEquals(group, Role.STUDENT);
//
//        students.stream()
//                .map(s -> new Homework(savedLesson, s))
//                .forEach(hw -> homeworkRepo.save(hw));
//    }
//}