package volm.journal.repo;


import org.springframework.data.repository.CrudRepository;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;


import java.util.List;

public interface HomeworkRepo extends CrudRepository<Homework, Long> {

    List<Homework> findAllByLessonEquals(Lesson lesson);

    List<Homework> findAllByLesson_Id(Long id);
}

