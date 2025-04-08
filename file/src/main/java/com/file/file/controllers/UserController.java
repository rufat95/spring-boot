package com.file.file.controllers;

import com.file.file.entities.User;
import com.file.file.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/fileUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public User createUser(@RequestPart("profilePicture") MultipartFile profilePicture,
                           @Valid @RequestParam String username,
                           @Valid @RequestParam String password) throws IOException {
        return userService.saveUser(profilePicture, username, password);
    }
}

