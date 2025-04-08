package com.ecommerce.Ecommerce.services;

import com.ecommerce.Ecommerce.entities.User;
import com.ecommerce.Ecommerce.enums.StatusCodes;
import com.ecommerce.Ecommerce.mappers.UserMappers;
import com.ecommerce.Ecommerce.repositories.UserRepository;
import com.ecommerce.Ecommerce.requests.UserCreateRequest;
import com.ecommerce.Ecommerce.resposes.UserCreateResponse;
import com.ecommerce.Ecommerce.resposes.UserImageResponse;
import com.ecommerce.Ecommerce.result.exceptions.BaseException;
import com.ecommerce.Ecommerce.result.success.SuccessDataResult;
import com.ecommerce.Ecommerce.result.success.SuccessResult;
import com.ecommerce.Ecommerce.utils.FileUploadValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMappers userMappers;
    private final FileUploadValidations fileUploadValidations;

    // This function for register user.
    public SuccessDataResult<UserCreateResponse> saveUser(
            @Valid UserCreateRequest userCreateRequest){
        User user1 = userRepository.findByNumber(userCreateRequest.getNumber());
        User user2 = userRepository.findByNumber(userCreateRequest.getWpNumber());
        User user3 = userRepository.findByWpNumber(userCreateRequest.getNumber());
        User user4 = userRepository.findByWpNumber(userCreateRequest.getWpNumber());

        if(user1 != null || user2 != null || user3 != null || user4 != null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCodes.USER_UNIQUE);
        }

        return new SuccessDataResult<>("User created successfully !",
            userMappers.changeUser(userRepository.save(userMappers.changeRequest(userCreateRequest))));
    }

    // This function for search user by number.
    public SuccessDataResult<UserImageResponse> getUserByNumber(String number) {
        User user = userRepository.findByNumber(number);
        if (user == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCodes.USER_NOT_FOUND);
        }
        return new SuccessDataResult<>("User found successfully",
                userMappers.changeUserImageResponse(user));
    }

    // This function for search user by whatsapp number.
    public SuccessDataResult<UserImageResponse> getUserByWpNumber(String wpNumber) {
//        CompletableFuture.supplyAsync(() ->);
        User user = userRepository.findByWpNumber(wpNumber);
        if (user == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCodes.USER_NOT_FOUND);
        }
        return new SuccessDataResult<>("User found successfully",
                userMappers.changeUserImageResponse(user));
    }

    // This function for user image setting.
    public SuccessDataResult<UserImageResponse> saveUserProfilePicture(
            Long id, MultipartFile profileImage) throws IOException {
        User user = userRepository.findById(id).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCodes.USER_NOT_FOUND));

        fileUploadValidations.userFileValidation(profileImage);

        final String UPLOAD_DIR = "users_image/";
        String fileName = UUID.randomUUID() + "_" + profileImage.getOriginalFilename();
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Files.copy(profileImage.getInputStream(), filePath);

        user.setProfilePicture(filePath.toString());
        User newUser = userRepository.save(user);
        UserImageResponse userImageResponse = userMappers.changeUserImageResponse(newUser);
        return new SuccessDataResult<>(
                "User profile picture was created successfully",
                userImageResponse);
    }

    // This function delete user by id.
    public SuccessResult deleteUser(Long id) {
        userRepository.deleteById(id);
        return new SuccessResult("User deleted successfully");
    }
    // This function get all users by pageable.
    public SuccessDataResult<List<UserImageResponse>> getAllUsers() {
        return new SuccessDataResult<>("All users get successfully",
                userMappers.allUsers(userRepository.findAll()));
    }
}