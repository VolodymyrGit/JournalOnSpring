package volm.journal.service;

import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;

import java.util.List;
import java.util.Map;

public interface HomeworkService {

    void setNewDescription(String description, Long hwId);

    Map<User, List<Homework>> mapHomeworks(List<Lesson> lessons);
}
