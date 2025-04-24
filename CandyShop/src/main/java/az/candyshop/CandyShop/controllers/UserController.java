package az.candyshop.CandyShop.controllers;

import az.candyshop.CandyShop.enums.UserRole;
import az.candyshop.CandyShop.requests.UserRequests.UserCreateRequest;
import az.candyshop.CandyShop.responses.UserResponses.UserCreateResponse;
import az.candyshop.CandyShop.responses.UserResponses.UserImageResponse;
import az.candyshop.CandyShop.responses.UserResponses.UserResponse;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessPageDataset;
import az.candyshop.CandyShop.result.success.SuccessResult;
import az.candyshop.CandyShop.services.UserService;
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

    @PostMapping(value = "/create_user")
    public SuccessDataResult<UserCreateResponse> createUser(
            @RequestBody @Valid UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest);
    }

    @PutMapping(value = "/upload_image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public SuccessDataResult<UserImageResponse> uploadUserImage(
            @RequestParam Long id,
            @RequestPart MultipartFile userImage
            ) throws IOException {
        return userService.uploadUserImage(id, userImage);
    }

    @GetMapping("{userEmail}")
    public SuccessDataResult<UserResponse> getOneUserByEmail(String userEmail){
        return userService.getOneUserByEmail(userEmail);
    }

    @GetMapping("/{page}/{size}")
    public SuccessPageDataset<List<UserResponse>> getAllUserList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {

        return userService.getAllUserList(page, size, sortBy, sortDirection);
    }

    @GetMapping("/find_users/{userRole}")
    public SuccessDataResult<List<UserResponse>> getAllUserRole(
            @RequestParam(defaultValue = "USER") UserRole userRole
    ){
        return userService.getAllUserRole(userRole);
    }

    @DeleteMapping("/delete_user/{id}")
    public SuccessResult deleteUser(@PathVariable Long id){
        return userService.deleteUser(id);
    }

}
