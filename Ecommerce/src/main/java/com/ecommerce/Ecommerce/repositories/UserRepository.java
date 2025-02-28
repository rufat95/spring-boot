package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByNumber(String number);
    User findByWpNumber(String wpNumber);
}
