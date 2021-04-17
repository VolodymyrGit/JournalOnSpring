package volm.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volm.journal.comparators.HomeworkComparator;
import volm.journal.model.Group;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.HomeworkRepo;
import volm.journal.repo.LessonRepo;
import volm.journal.repo.UserRepo;
import volm.journal.security.Role;
import volm.journal.service.HomeworkService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RequiredArgsConstructor
@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final LessonRepo lessonRepo;
    private final HomeworkRepo homeworkRepo;
    private final UserRepo userRepo;


    @Override
    public void setNewDescription(String description, Long hwId) {

        Homework homeWork = homeworkRepo.findById(hwId)
                .orElseThrow(() -> new NoSuchElementException());

        homeWork.setHwDescription(description);
        homeworkRepo.save(homeWork);
    }


    @Override
    public Map<User, List<Homework>> mapHomeworks(List<Lesson> lessons) {

        Map<User, List<Homework>> homeworks = lessons.stream()
                .map(lesson -> homeworkRepo.findAllByLessonEquals(lesson))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(hw -> hw.getStudent(), Collectors.toList()));

        return homeworks;
    }


    @Override
    public List<Homework> findAllHomeworksInGroup(List<Lesson> lessons) {

        return lessons.stream()
                .map(lesson -> homeworkRepo.findAllByLessonEquals(lesson))
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }


    @Override
    public Map<User, List<Homework>> findHomeworksForEachStudent(List<User> students) {

        return students.stream()
                .collect(Collectors
                        .toMap(student -> student,
                        student -> homeworkRepo.findAllByStudentEquals(student).stream()
                                .sorted(new HomeworkComparator())
                                .collect(Collectors.toList())));
    }
}