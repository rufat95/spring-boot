package com.rufat_ecomers.ecomers.repositories;

import com.rufat_ecomers.ecomers.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
