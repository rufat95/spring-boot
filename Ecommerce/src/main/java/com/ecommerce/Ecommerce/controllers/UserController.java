package com.ecommerce.Ecommerce.controllers;

import com.ecommerce.Ecommerce.requests.UserCreateRequest;
import com.ecommerce.Ecommerce.resposes.UserCreateResponse;
import com.ecommerce.Ecommerce.resposes.UserImageResponse;
import com.ecommerce.Ecommerce.result.success.SuccessDataResult;
import com.ecommerce.Ecommerce.result.success.SuccessResult;
import com.ecommerce.Ecommerce.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping(value = "/user_save")
    public SuccessDataResult<UserCreateResponse> saveUser(
            @RequestBody @Valid UserCreateRequest userCreateRequest){
        return userService.saveUser(userCreateRequest);
    }

    @PutMapping(value = "/upload_image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessDataResult<UserImageResponse> saveUserProfilePicture(
            @RequestParam Long id,
            @RequestPart MultipartFile profileImage) throws IOException {
        return userService.saveUserProfilePicture(id, profileImage);
    }

    @GetMapping("/byNumber/{number}")
    public SuccessDataResult<UserImageResponse> getUserByNumber(@PathVariable String number){
        return userService.getUserByNumber(number);
    }

    @GetMapping("/byWhatsappNumber/{wpNumber}")
    public SuccessDataResult<UserImageResponse> getUserByWpNumber(@PathVariable String wpNumber){
        return userService.getUserByWpNumber(wpNumber);
    }

    @DeleteMapping("/{id}")
    public SuccessResult deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

    @GetMapping("/all_users")
    public SuccessDataResult<List<UserImageResponse>> getAllUsers(){
        return userService.getAllUsers();
    }
}