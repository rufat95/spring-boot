package com.verification.Verification.services;

import com.verification.Verification.config.MailServer;
import com.verification.Verification.entities.User;
import com.verification.Verification.enums.UserStatus;
import com.verification.Verification.repostories.UserRepository;
import com.verification.Verification.requests.UserRequests;
import com.verification.Verification.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailServer mailServer;
    Random random = new Random();

    public UserResponse saveUser(UserRequests userRequests) {
        User user = new User();
        Integer otp = random.nextInt(100_000, 999_999);

        user.setUsername(userRequests.getUsername());
        user.setEmail(userRequests.getEmail());
        user.setOtp(otp);
        user.setUserStatus(UserStatus.IN_ACTIVE);

        mailServer.mailSend(userRequests.getEmail(),
                "Your otp Code", String.valueOf(otp));

        User newUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(newUser.getUsername());
        userResponse.setEmail(newUser.getEmail());

        return userResponse;
    }

    public UserResponse activateStatus(Integer otpCode) {
        User user = userRepository.findByOtp(otpCode);

        if(user == null){
            throw new RuntimeException("User not found. Please sign up first");
        }

        user.setUserStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(user.getUsername());
        userResponse.setEmail(user.getEmail());

        return userResponse;
    }
}
