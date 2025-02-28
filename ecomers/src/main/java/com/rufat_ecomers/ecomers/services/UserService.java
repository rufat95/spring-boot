package com.rufat_ecomers.ecomers.services;

import com.rufat_ecomers.ecomers.entities.User;
import com.rufat_ecomers.ecomers.enums.StatusCode;
import com.rufat_ecomers.ecomers.exceptions.BaseException;
import com.rufat_ecomers.ecomers.repositories.UserRepository;
import com.rufat_ecomers.ecomers.requests.UserRequest;
import com.rufat_ecomers.ecomers.responses.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getOneUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND));
    }

    public UserResponse createUser(UserRequest userRequest) {
        User user = new User();

        user.setName(userRequest.getUsername());
        user.setPassword(userRequest.getPassword());
        user.setEmail(userRequest.getEmail());

        User newUser = userRepository.save(user);

        UserResponse userResponse = new UserResponse();
        userResponse.setUsername(newUser.getName());
        userResponse.setEmail(newUser.getEmail());
        userResponse.setPassword(newUser.getPassword());

        return userResponse;
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
