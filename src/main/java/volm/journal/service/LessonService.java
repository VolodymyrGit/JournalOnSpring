package volm.journal.service;

import org.springframework.stereotype.Service;
import volm.journal.model.Group;
import volm.journal.model.User;


@Service
public interface LessonService {

    void addLesson(Group group);
}