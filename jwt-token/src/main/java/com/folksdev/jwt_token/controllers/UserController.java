package com.folksdev.jwt_token.controllers;

import com.folksdev.jwt_token.model.User;
import com.folksdev.jwt_token.requests.AuthRequest;
import com.folksdev.jwt_token.requests.CreateUserRequests;
import com.folksdev.jwt_token.services.JwtService;
import com.folksdev.jwt_token.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @GetMapping("/welcome")
    public String welcome(){
        return "This is welcome!";
    }

    @PostMapping("/addNewUser")
    public User addUser(@RequestBody CreateUserRequests requests){
        return userService.createUser(requests);
    }

    @PostMapping("/generateToken")
    public String generateToken(@RequestBody AuthRequest request){
        return userService.generateToken(request);
    }

    @GetMapping("/user")
    public String getUserString(){
        return "This is User";
    }

    @GetMapping("/admin")
    public String getAdminString(){
        return "This is Admin";
    }
}



















