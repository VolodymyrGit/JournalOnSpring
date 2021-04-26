package volm.journal.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;


import java.util.List;


@Repository
public interface HomeworkRepo extends CrudRepository<Homework, Long> {

    List<Homework> findAllByLessonEquals(Lesson lesson);

    List<Homework> findAllByLesson_Id(Long id);
}

