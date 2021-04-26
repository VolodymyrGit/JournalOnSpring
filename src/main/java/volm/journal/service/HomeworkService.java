package volm.journal.service;

import org.springframework.stereotype.Service;
import volm.journal.model.Homework;
import volm.journal.model.User;

import java.util.List;
import java.util.Map;


@Service
public interface HomeworkService {


    Homework changeDescription(User currentUser, Long hwId, String hwDescription);

    Map<User, List<Homework>> findHomeworksForEachStudent(List<User> students);
}