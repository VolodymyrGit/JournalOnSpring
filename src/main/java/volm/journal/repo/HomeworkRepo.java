package volm.journal.repo;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volm.journal.model.Homework;
import volm.journal.model.Lesson;
import volm.journal.model.User;


import java.util.List;


@Repository
public interface HomeworkRepo extends CrudRepository<Homework, Long> {

    List<Homework> findAllByLessonEquals(Lesson lesson);

    List<Homework> findAllByStudentEquals(User student);

    Homework findByLessonEqualsAndStudentEquals(Lesson lesson, User student);

    List<Homework> findAllByLesson_Id(Long id);
}

