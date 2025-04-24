package az.candyshop.CandyShop.services;

import az.candyshop.CandyShop.config.MailServer;
import az.candyshop.CandyShop.entities.Otp;
import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.enums.UserRole;
import az.candyshop.CandyShop.repositories.OtpRepository;
import az.candyshop.CandyShop.repositories.UserRepository;
import az.candyshop.CandyShop.requests.OtpRequests.OtpSendingRequest;
import az.candyshop.CandyShop.requests.UserRequests.AuthLoginRequest;
import az.candyshop.CandyShop.requests.UserRequests.AuthRegisterRequest;
import az.candyshop.CandyShop.requests.UserRequests.UserPassRequest;
import az.candyshop.CandyShop.responses.UserResponses.AuthLoginResponse;
import az.candyshop.CandyShop.result.exception.BaseException;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessResult;
import az.candyshop.CandyShop.security.JwtUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final OtpRepository otpRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final MailServer mailServer;
    Random random = new Random();

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

    public SuccessDataResult<AuthLoginResponse> login(
            @Valid AuthLoginRequest authLoginRequest) {
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
        authLoginResponse.setUserImage(user.getUserImage());
        return new SuccessDataResult<>("You are log in successfully !",
                authLoginResponse);
    }

    public SuccessResult forgetPassword(@Valid OtpSendingRequest otpSendingRequest) {
        User user = userRepository.findByEmail(otpSendingRequest.getEmail());
        if(user == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND);
        }
        Integer otpCode = random.nextInt(100_000, 999_999);
        mailServer.mailSend(otpSendingRequest.getEmail(),
                "Your otp Code", String.valueOf(otpCode));
        Otp otp = new Otp();
        otp.setUser(user);
        otp.setOtp(otpCode);
        otpRepository.save(otp);
        return new SuccessResult("Your otp code send to your email. " +
                "Please enter that otp code.");
    }

    public SuccessResult otpChangePassword(Integer otp,
                                           UserPassRequest userPassRequest) {
        Otp thatOtp = otpRepository.findByOtp(otp);
        if (thatOtp == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.OTP_CONFIRM);
        }

        User userPassword = userRepository.findByEmail(thatOtp.getUser().getEmail());
        userPassword.setPassword(passwordEncoder.encode(userPassRequest.getNewPassword()));
        userRepository.save(userPassword);
        return new SuccessResult("Your password changed successfully");
    }
}
