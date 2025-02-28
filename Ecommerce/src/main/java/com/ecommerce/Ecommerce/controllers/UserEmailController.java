package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.requests.UserEmailRequest;
import com.ecommerce.Ecommerce.result.success.SuccessResult;
import com.ecommerce.Ecommerce.services.UserEmailService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/user_emails")
public class UserEmailController {
    private final UserEmailService userEmailService;

    @PostMapping
    public SuccessResult saveEmail(
            @RequestParam Long id,
            @RequestBody @Valid UserEmailRequest userEmailRequest){
        return userEmailService.saveEmail(id, userEmailRequest);
    }
}
