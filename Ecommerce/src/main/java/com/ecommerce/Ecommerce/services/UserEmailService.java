package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.config.MailServer;
import com.ecommerce.Ecommerce.entities.User;
import com.ecommerce.Ecommerce.entities.UserEmail;
import com.ecommerce.Ecommerce.enums.StatusCodes;
import com.ecommerce.Ecommerce.repositories.UserEmailRepository;
import com.ecommerce.Ecommerce.repositories.UserRepository;
import com.ecommerce.Ecommerce.requests.UserEmailRequest;
import com.ecommerce.Ecommerce.result.exceptions.BaseException;
import com.ecommerce.Ecommerce.result.success.SuccessResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEmailService {
    private final UserEmailRepository userEmailRepository;
    private final UserRepository userRepository;

    public SuccessResult saveEmail(
            Long id,
            @Valid UserEmailRequest userEmailRequest) {

        User user = userRepository.findById(id).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCodes.USER_NOT_FOUND));

        UserEmail userEmail = new UserEmail();
        userEmail.setEmail(userEmailRequest.getEmail());
        userEmail.setUser(user);
        userEmailRepository.save(userEmail);
        return new SuccessResult("User email added successfully.");
    }
}
