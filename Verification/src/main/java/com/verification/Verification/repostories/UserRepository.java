package com.verification.Verification.repostories;

import com.verification.Verification.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByOtp(Integer otpCode);
}
