package volm.journal.repo;

import org.springframework.data.repository.CrudRepository;
import volm.journal.model.Group;

public interface GroupRepo extends CrudRepository<Group, Long> {

}
