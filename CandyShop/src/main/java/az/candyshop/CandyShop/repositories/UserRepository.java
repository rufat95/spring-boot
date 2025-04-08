package az.candyshop.CandyShop.repositories;

import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.enums.UserRole;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    List<User> findByUserRole(UserRole userRole);

    @EntityGraph(attributePaths = "userRole")
    Optional<User> findByUsername(String username);
    Boolean existsByEmail(String email);

}
