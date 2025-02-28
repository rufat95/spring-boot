package az.texnoera.texnoera.repositories;

import az.texnoera.texnoera.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String userEmail);
    List<User> findByFirstNameStartingWith(String firstName);
    User findUserByEmailAndPassword(String userEmail, String userPassword);
}
