package volm.journal.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import volm.journal.security.Role;
import volm.journal.model.Group;
import volm.journal.model.User;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepo extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    List<User> findAllByRolesIsNotContaining(Role role);

    List<User> findAllByGroupEquals(Group group);

    List<User> findAllByGroup_Id(Long id);

    List<User> findAllByGroupEqualsAndRolesContaining(Group group, Role role);

    List<User> findAllByRolesContains(Role role);

    Optional<User> findByEmailEquals(String email);
}