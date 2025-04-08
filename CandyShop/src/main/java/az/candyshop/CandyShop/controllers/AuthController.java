package az.candyshop.CandyShop.controllers;

import az.candyshop.CandyShop.requests.UserRequests.AuthLoginRequest;
import az.candyshop.CandyShop.requests.UserRequests.AuthRegisterRequest;
import az.candyshop.CandyShop.responses.UserResponses.AuthLoginResponse;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
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
    public SuccessDataResult<AuthLoginResponse> login(@RequestBody @Valid AuthLoginRequest authLoginRequest){
        return authService.login(authLoginRequest);
    }
}
