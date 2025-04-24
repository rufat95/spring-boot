package com.example.springsecurity3.service;

import com.example.springsecurity3.entity.Role;
import com.example.springsecurity3.entity.User;
import com.example.springsecurity3.model.request.LoginRequest;
import com.example.springsecurity3.model.request.UserRequest;
import com.example.springsecurity3.respository.RoleRepository;
import com.example.springsecurity3.respository.UserRepository;
import com.example.springsecurity3.security.utilities.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtUtils jwtUtils;


    public String register(UserRequest userRequest) {
        User user = new User();
        if(userRepository.existsByUsername(userRequest.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        user.setUsername(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER");
        user.setRoles(Set.of(role));
        userRepository.save(user);
        return "Successfully registered";
    }

    public String login(LoginRequest loginRequest){
        User user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new RuntimeException("User not fount with username: " + loginRequest.getUsername()));


        if(!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("username or password incorrect");
        }

        return jwtUtils.generateJwtToken(user.getUsername(),
                user.getRoles().stream().map(Role::getName).toList());

    }
}
