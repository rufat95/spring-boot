package az.other.Other.controllers;

import az.other.Other.entities.User;
import az.other.Other.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {
    private UserService userService;

    /*
    * Read all users
    */
    @GetMapping
    public List<User> getAllUsers(){
        return this.userService.getAllUsers();
    }

    /*
     * Create
     */
    @PostMapping
    public User register(@RequestBody User newUser){
        return this.userService.register(newUser);
    }

    /*
     * Read one user
     */
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return this.userService.getOneUserById(userId);
    }

    /*
     * Update
     */
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser){
        return this.userService.updateOneUser(userId, newUser);
    }

    /*
     * Delete
     */
    @DeleteMapping("/{userId}")
    public void deleteOneUser(@PathVariable Long userId){
        this.userService.deleteOneUser(userId);
    }
}
