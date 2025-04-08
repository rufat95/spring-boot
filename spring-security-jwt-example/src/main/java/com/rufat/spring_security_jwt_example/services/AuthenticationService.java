package com.rufat.spring_security_jwt_example.services;

import com.rufat.spring_security_jwt_example.dto.UserDto;
import com.rufat.spring_security_jwt_example.dto.UserRequest;
import com.rufat.spring_security_jwt_example.dto.UserResponse;
import com.rufat.spring_security_jwt_example.entities.User;
import com.rufat.spring_security_jwt_example.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public UserResponse save(UserDto userDto) {
        User user = User.builder()
                .nameSurname(userDto.getNameSurname())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();

        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return UserResponse.builder().token(token).build();
    }

    public UserResponse auth(UserRequest userRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                userRequest.getUsername(),
                userRequest.getPassword()
        ));

        User user = userRepository.findByUsername(userRequest.getUsername()).orElseThrow();
        String token = jwtService.generateToken(user);
        return UserResponse.builder().token(token).build();
    }
}
