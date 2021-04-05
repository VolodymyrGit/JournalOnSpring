package volm.journal.repo;

import org.springframework.data.repository.CrudRepository;
import volm.journal.enums.Role;
import volm.journal.model.Group;
import volm.journal.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends CrudRepository<User, Long> {

    List<User> findAllByGroupEquals(Group group);

    List<User> findAllByGroup_Id(Long id);

    List<User> findAllByRoleEquals(Role role);

    Optional<User> findByEmailEquals(String email);
}