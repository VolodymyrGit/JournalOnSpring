package volm.journal.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volm.journal.model.Group;
import volm.journal.model.Lesson;


import java.util.List;


@Repository
public interface LessonRepo extends CrudRepository<Lesson, Long> {

    List<Lesson> findAllByGroupEquals(Group group);
}