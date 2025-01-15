package az.other.Other.controllers;

import az.other.Other.entities.User;
import az.other.Other.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@AllArgsConstructor
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
    @GetMapping("/{user_id}")
    public User getOneUser(@PathVariable Long user_id){
        return this.userService.getOneUser(user_id);
    }

    /*
     * Update
     */
    @PutMapping("/{user_id}")
    public User updateOneUser(@PathVariable Long user_id, @RequestBody User newUser){
        return this.userService.updateOneUser(user_id, newUser);
    }

    /*
     * Delete
     */
    @DeleteMapping("/{user_id}")
    public void deleteOneUser(@PathVariable Long user_id){
        this.userService.deleteOneUser(user_id);
    }
}
