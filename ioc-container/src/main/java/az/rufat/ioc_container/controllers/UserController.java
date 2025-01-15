package az.rufat.ioc_container.controllers;

import az.rufat.ioc_container.entities.User;
import az.rufat.ioc_container.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    public List<User> getUsers(){
       return userService.findAll();
   }

   @PostMapping
    public void createUser(@RequestBody User user){
       this.userService.save(user);
   }

}
