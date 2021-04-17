package volm.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volm.journal.security.Role;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.service.LessonService;
import java.util.Date;
import java.util.List;


@RequiredArgsConstructor
@Service
public class LessonServiceImpl implements LessonService {

    private final LessonRepo lessonRepo;
    private final UserRepo userRepo;
    private final HomeworkRepo homeworkRepo;


    @Override
    public void addLesson(Group group) {

        Lesson newLesson = lessonRepo.save(new Lesson(group, new Date()));

        List<User> students = userRepo.findAllByGroupEqualsAndRolesContaining(group, Role.STUDENT);

        students.stream()
                .map(student -> new Homework(newLesson, student))
                .forEach(homeworkRepo::save);
    }
}