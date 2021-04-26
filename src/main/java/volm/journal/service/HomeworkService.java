package volm.journal.service;

import org.springframework.stereotype.Service;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;

import java.util.List;
import java.util.Map;


@Service
public interface HomeworkService {

    void setNewDescription(String description, Long hwId);

    Map<User, List<Homework>> mapHomeworks(List<Lesson> lessons);

    List<Homework> findAllHomeworksInGroup(List<Lesson> lessons);

    Map<User, List<Homework>> findHomeworksForEachStudent(List<User> students);
}