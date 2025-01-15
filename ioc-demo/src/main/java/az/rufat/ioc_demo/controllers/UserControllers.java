package az.rufat.ioc_demo.controllers;

import az.rufat.ioc_demo.entities.User;
import az.rufat.ioc_demo.services.UserServices;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class UserControllers {
    private final UserServices userServices;

    @GetMapping
    public List<User> getUsers(){
        return userServices.findAll();
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userServices.save(user);
    }
}
