package az.texnoera.texnoera.controllers;

import az.texnoera.texnoera.entities.User;
import az.texnoera.texnoera.requests.UserLoginRequest;
import az.texnoera.texnoera.requests.UserRequest;
import az.texnoera.texnoera.responses.UserLoginResponse;
import az.texnoera.texnoera.services.UserService;
import az.texnoera.texnoera.utils.result.DataResult;
import az.texnoera.texnoera.utils.result.Result;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public User createUser(@RequestBody @Valid UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @GetMapping("/{userEmail}")
    public User getOneUserByEmail(@PathVariable String userEmail){
        return userService.getOneUserByEmail(userEmail);
    }

    @GetMapping("/like/{firstName}")
    public List<User> findUsersByFirstName(@RequestParam String firstName){
        return userService.findUsersByFirstName(firstName);
    }

    @PutMapping("/{userId}")
    public User updateUser(@RequestParam Long userId,
                           @RequestBody @Valid UserRequest userRequest){
        return userService.updateUser(userId, userRequest);
    }

    @DeleteMapping("/{userId}")
    public Result deleteUser(@PathVariable Long userId){
        return userService.deleteUser(userId);
    }

    @PostMapping("/login")
    public DataResult<UserLoginResponse> login(@RequestBody @Valid UserLoginRequest userLoginRequest){
        return userService.login(userLoginRequest);
    }


}
