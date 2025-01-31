package az.supertodo.Todo.controllers;

import az.supertodo.Todo.requests.UserLoginRequest;
import az.supertodo.Todo.responses.UserLoginResponse;
import az.supertodo.Todo.usefull.SuccessResult;
import az.supertodo.Todo.entities.User;
import az.supertodo.Todo.requests.UserCreateRequest;
import az.supertodo.Todo.requests.UserUpdateRequest;
import az.supertodo.Todo.responses.UserCreateResponse;
import az.supertodo.Todo.responses.UserUpdateResponse;
import az.supertodo.Todo.services.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;

    @PostMapping
    public UserCreateResponse createUser(@RequestBody @Valid UserCreateRequest userCreateRequest){
        return userService.createUser(userCreateRequest);
    }

    @PutMapping("/{userId}")
    public UserUpdateResponse updateUser(@RequestParam Long userId,
                                         @Valid @RequestBody UserUpdateRequest userUpdateRequest){
        return userService.updateUser(userId, userUpdateRequest);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }

    @GetMapping("/findByEmail/{userEmail}")
    public List<User> getUsers(@RequestParam(required = false) String userEmail){
        return userService.getUsers(userEmail);
    }

    @GetMapping("/{id}")
    public User getUsers(@PathVariable Long id){
        return userService.getUserFindById(id);
    }

    @PostMapping("/login")
    public SuccessResult<UserLoginResponse> logIn(@RequestBody UserLoginRequest userLoginRequest) {
        return userService.logIn(userLoginRequest);
    }

}
