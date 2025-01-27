package az.supertodo.Todo.repositories;

import az.supertodo.Todo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    Boolean existsByEmail(String email);
    List<User> findAllByEmail(String email);
    User findByUsername(String username);
    User findByEmail(String email);
}
