package com.example.Practice.repos;

import com.example.Practice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepositories extends JpaRepository<User, Integer> {
    Boolean existsByUsername(String username);
    User findByUsername(String username);
}
