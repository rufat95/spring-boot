package com.example.Practice.controllers;

import com.example.Practice.entities.User;
import com.example.Practice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    public List<User> getUsers(){
        return userService.findAll();
    }

    @GetMapping("/{user_id}")
    public Optional<User> getOneUser(Integer user_id){
        return userService.getOneUser(user_id);
    }

    @PostMapping("/register")
    public void createUser(@RequestBody User user){
        userService.save(user);
    }

    @PostMapping("/login")
    public String logIn(@RequestBody User user) {
        User user1 = userService.logIn(user);
        if (user1 != null) {
            return "Login successful! User ID: " + user1.getUser_id() + " " + user1.getUsername();
        } else {
            return "User not found!";
        }
    }

    @PutMapping("/{user_id}")
    public User updateOneUser(@PathVariable Integer user_id, @RequestBody User newUser){
        return this.userService.updateOneUser(user_id, newUser);
    }

    @DeleteMapping("/{user_id}")
    public void deleteUser(Integer userID){
        this.userService.deleteUser(userID);
    }

}
