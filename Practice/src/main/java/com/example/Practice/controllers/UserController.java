package com.example.Practice.controllers;

import com.example.Practice.entities.User;
import com.example.Practice.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.save(user);
    }

    @GetMapping("/login")
    public void logIn(User user){
        userService.logIn(user);
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
