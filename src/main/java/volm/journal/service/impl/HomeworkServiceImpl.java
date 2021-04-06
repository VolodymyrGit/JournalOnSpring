package volm.journal.service.impl;

import org.springframework.stereotype.Service;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.repo.HomeworkRepo;
import volm.journal.service.HomeworkService;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class HomeworkServiceImpl implements HomeworkService {

    private HomeworkRepo homeworkRepo;

    public void setHomeworkRepo(HomeworkRepo homeworkRepo) {
        this.homeworkRepo = homeworkRepo;
    }

    @Override
    public void setNewDescription(String description, Long hwId) {

        Homework homeWork = homeworkRepo.findById(hwId)
                .orElseThrow(() -> new NoSuchElementException());

        homeWork.setHwDescription(description);
        homeworkRepo.save(homeWork);
    }

    @Override
    public Map<Long, List<Homework>> homeworksMap(List<Lesson> lessons) {

        Map<Long, List<Homework>> homeworks = lessons.stream()
                .map(l -> l.getId())
                .collect(Collectors.toList()).stream()
                .map(id -> homeworkRepo.findAllByLesson_Id(id))
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(hw -> hw.getStudent().getId(), Collectors.toList()));

        return homeworks;
    }
}
