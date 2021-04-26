package volm.journal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import volm.journal.comparators.HomeworkComparator;
import volm.journal.comparators.UserComparator;
import volm.journal.exceptions.EntityNotFoundException;
import volm.journal.model.Homework;
import volm.journal.model.User;
import volm.journal.repo.HomeworkRepo;
import volm.journal.service.HomeworkService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class HomeworkServiceImpl implements HomeworkService {

    private final HomeworkRepo homeworkRepo;


    @Override
    public Homework changeDescription(User currentUser, Long hwId, String hwDescription) {

        Homework homework = homeworkRepo.findById(hwId)
                .orElseThrow(() -> new EntityNotFoundException(Homework.class));

        if(currentUser.getId() == homework.getStudent().getId()) {

            homework.setHwDescription(hwDescription);
            homeworkRepo.save(homework);
        }
        return homework;
    }


    @Override
    public Map<User, List<Homework>> findHomeworksForEachStudent(List<User> students) {

        Map<User, List<Homework>> studentsHomeworks = students.stream()
                .collect(Collectors
                        .toMap(student -> student,
                                student -> homeworkRepo.findAllByStudentEquals(student).stream()
                                        .sorted(new HomeworkComparator())
                                        .collect(Collectors.toList())));

        TreeMap<User, List<Homework>> studentsHomeworksTreeMap = new TreeMap<>(new UserComparator());

        studentsHomeworksTreeMap.putAll(studentsHomeworks);

        return studentsHomeworksTreeMap;
    }
}