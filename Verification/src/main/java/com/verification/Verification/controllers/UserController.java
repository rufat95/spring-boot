package com.verification.Verification.controllers;

import com.verification.Verification.requests.UserRequests;
import com.verification.Verification.responses.UserResponse;
import com.verification.Verification.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse saveUser(@RequestBody UserRequests userRequests){
        return userService.saveUser(userRequests);
    }

    @PutMapping("/otp_code")
    public UserResponse activateStatus(@RequestParam Integer otpCode){
        return userService.activateStatus(otpCode);
    }
}
