package com.rufat.spring_security_jwt_example.repositories;

import com.rufat.spring_security_jwt_example.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
