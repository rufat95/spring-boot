package az.candyshop.CandyShop.services;

import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.enums.UserRole;
import az.candyshop.CandyShop.mappers.UserMappers;
import az.candyshop.CandyShop.repositories.UserRepository;
import az.candyshop.CandyShop.requests.UserRequests.UserCreateRequest;
import az.candyshop.CandyShop.responses.UserResponses.UserCreateResponse;
import az.candyshop.CandyShop.responses.UserResponses.UserImageResponse;
import az.candyshop.CandyShop.responses.UserResponses.UserResponse;
import az.candyshop.CandyShop.result.exception.BaseException;
import az.candyshop.CandyShop.result.PaginationResponse;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessPageDataset;
import az.candyshop.CandyShop.result.success.SuccessResult;
import az.candyshop.CandyShop.utils.ImageUploadValidations;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserMappers userMappers;
    private final ImageUploadValidations imageUploadValidations;
    private final PasswordEncoder passwordEncoder;

    //  Post api for create new user
    public SuccessDataResult<UserCreateResponse> createUser(
            @Valid UserCreateRequest userCreateRequest) {
        User existingUser  = userRepository.findByEmail(userCreateRequest.getEmail());
        if (existingUser  != null){
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.USER_UNIQUE);
        }

        User newUser = userMappers.changeUserCreateRequestToUser(userCreateRequest);
        String encodedPassword = passwordEncoder.encode(userCreateRequest.getPassword());
        newUser.setPassword(encodedPassword);
        User savedUser = userRepository.save(newUser);
        UserCreateResponse response = userMappers.changeUserToUserCreateResponse(savedUser);
        return new SuccessDataResult<>("User created successfully !", response);
    }

    // Put api for upload or update user profile picture
    public SuccessDataResult<UserImageResponse> uploadUserImage
            (Long id, MultipartFile userImage) throws IOException {

        User user = userRepository.findById(id).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND));
        imageUploadValidations.userImageUpload(userImage, user);
        return new SuccessDataResult<>("Your image uploaded successfully.",
                userMappers.changeUserToUserImageResponse(userRepository.save(user)));
    }

    // Get api for get one user by email
    public SuccessDataResult<UserResponse> getOneUserByEmail(String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND);
        }
        return new SuccessDataResult<>("User found successfully.",
                userMappers.changeUserToUserResponse(user));
    }

    // Get api fot get all users with pagination
    public SuccessPageDataset<List<UserResponse>> getAllUserList(
            int page, int size, String sortBy, String sortDirection) {

        Sort.Direction direction = sortDirection.equalsIgnoreCase("desc")
                ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<User> userPage = userRepository.findAll(pageable);
        List<UserResponse> userResponses = userMappers.changePageUserToUserResponse(userPage);
        PaginationResponse pagination = new PaginationResponse(
                userPage.getNumber(),
                userPage.getSize(),
                userPage.getTotalElements(),
                userPage.getTotalPages()
        );
        return new SuccessPageDataset<>(
                true,
                "Users fetched successfully",
                userResponses,
                pagination
        );
    }

    // Get api for by role users
    public SuccessDataResult<List<UserResponse>> getAllUserRole(UserRole userRole) {
        return new SuccessDataResult<>("User fetched successfully",
                userMappers.changeListUserToListUserResponse(
                        userRepository.findByUserRole(userRole)));
    }

    // Delete api for delete user
    public SuccessResult deleteUser(Long id) {
        userRepository.deleteById(id);
        return new SuccessResult("User delete successfully");
    }
}
