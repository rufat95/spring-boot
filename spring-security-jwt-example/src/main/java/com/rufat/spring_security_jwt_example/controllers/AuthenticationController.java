package com.rufat.spring_security_jwt_example.controllers;

import com.rufat.spring_security_jwt_example.dto.UserDto;
import com.rufat.spring_security_jwt_example.dto.UserRequest;
import com.rufat.spring_security_jwt_example.dto.UserResponse;
import com.rufat.spring_security_jwt_example.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserDto userDto){
        return ResponseEntity.ok(authenticationService.save(userDto));
    }

    @PostMapping("/auth")
    public ResponseEntity<UserResponse> auth(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

}
