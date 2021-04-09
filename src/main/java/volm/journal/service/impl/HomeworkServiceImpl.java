package volm.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;
import volm.journal.repo.HomeworkRepo;
import volm.journal.service.HomeworkService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepo homeworkRepo;


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
}
