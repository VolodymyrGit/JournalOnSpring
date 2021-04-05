package volm.journal.repo;

import org.springframework.data.repository.CrudRepository;
import volm.journal.model.Group;
import volm.journal.model.Lesson;


import java.util.List;


public interface LessonRepo extends CrudRepository<Lesson, Long> {

    List<Lesson> findAllByGroupEquals(Group group);
}