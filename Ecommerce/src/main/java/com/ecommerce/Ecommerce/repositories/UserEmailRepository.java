package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.entities.UserEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEmailRepository extends JpaRepository<UserEmail, Long> {
}
