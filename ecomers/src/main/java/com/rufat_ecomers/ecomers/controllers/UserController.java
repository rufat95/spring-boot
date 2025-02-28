package com.rufat_ecomers.ecomers.controllers;

import com.rufat_ecomers.ecomers.entities.User;
import com.rufat_ecomers.ecomers.requests.UserRequest;
import com.rufat_ecomers.ecomers.responses.UserResponse;
import com.rufat_ecomers.ecomers.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public User getOneUserById(@PathVariable Long userId){
        return userService.getOneUserById(userId);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody @Valid UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}
