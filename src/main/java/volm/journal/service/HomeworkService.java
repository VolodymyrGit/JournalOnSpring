package volm.journal.service;

import volm.journal.model.Homework;
import volm.journal.model.Lesson;

import java.util.List;
import java.util.Map;

public interface HomeworkService {

    void setNewDescription(String description, Long hwId);

    Map<Long, List<Homework>> homeworksMap(List<Lesson> lessons);
}
