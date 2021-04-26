package volm.journal.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volm.journal.model.Group;


@Repository
public interface GroupRepo extends CrudRepository<Group, Long> {

}
