package az.candyshop.CandyShop.controllers;

import az.candyshop.CandyShop.requests.OtpRequests.OtpSendingRequest;
import az.candyshop.CandyShop.requests.UserRequests.AuthLoginRequest;
import az.candyshop.CandyShop.requests.UserRequests.AuthRegisterRequest;
import az.candyshop.CandyShop.requests.UserRequests.UserPassRequest;
import az.candyshop.CandyShop.responses.UserResponses.AuthLoginResponse;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessResult;
import az.candyshop.CandyShop.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public String register(@RequestBody @Valid AuthRegisterRequest authRegisterRequest) {
        return authService.register(authRegisterRequest);
    }

    @PostMapping("/login")
    public SuccessDataResult<AuthLoginResponse> login(
            @RequestBody @Valid AuthLoginRequest authLoginRequest){
        return authService.login(authLoginRequest);
    }

    @PostMapping("/forget_password")
    public SuccessResult forgetPassword(@RequestBody @Valid OtpSendingRequest otpSendingRequest){
        return authService.forgetPassword(otpSendingRequest);
    }

    @PutMapping("/otp_change_password")
    public SuccessResult otpChangePassword(
            @RequestParam Integer otp, @RequestBody @Valid UserPassRequest userPassRequest){
        return authService.otpChangePassword(otp, userPassRequest);
    }
}
