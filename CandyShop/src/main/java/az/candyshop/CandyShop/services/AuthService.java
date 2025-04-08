package az.candyshop.CandyShop.services;

import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.enums.UserRole;
import az.candyshop.CandyShop.repositories.UserRepository;
import az.candyshop.CandyShop.requests.UserRequests.AuthLoginRequest;
import az.candyshop.CandyShop.requests.UserRequests.AuthRegisterRequest;
import az.candyshop.CandyShop.responses.UserResponses.AuthLoginResponse;
import az.candyshop.CandyShop.result.exception.BaseException;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;

    public String register(@Valid AuthRegisterRequest authRegisterRequest) {
        User user = userRepository.findByEmail(authRegisterRequest.getEmail());

        if(user != null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.USER_UNIQUE);
        }

        User newUser = new User();
        newUser.setUsername(authRegisterRequest.getUsername());
        newUser.setEmail(authRegisterRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(authRegisterRequest.getPassword()));
        newUser.setNumber(authRegisterRequest.getNumber());
        newUser.setUserRole(UserRole.ROLE_USER);
        userRepository.save(newUser);

        return "You registered successfully. Please first sign in.";
    }

    public SuccessDataResult<AuthLoginResponse> login(@Valid AuthLoginRequest authLoginRequest) {
        User user = userRepository.findByEmail(authLoginRequest.getEmail());
        if(user == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND);
        }
        if(!passwordEncoder.matches(authLoginRequest.getPassword(), user.getPassword())) {
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_SIGN_IN);
        }
        String token = jwtUtils.generateJwtToken(user.getUsername(), List.of(user.getUserRole().name()));

        AuthLoginResponse authLoginResponse = new AuthLoginResponse();
        authLoginResponse.setId(user.getId());
        authLoginResponse.setUsername(user.getUsername());
        authLoginResponse.setEmail(user.getEmail());
        authLoginResponse.setNumber(user.getNumber());
        authLoginResponse.setToken(token);
        return new SuccessDataResult<>("You are log in successfully !",
                authLoginResponse);
    }
}
