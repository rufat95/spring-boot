package az.texnoera.texnoera.entity.userController;

import az.texnoera.texnoera.entity.services.UserService;
import az.texnoera.texnoera.entity.user.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService = new UserService();
    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }
    @PostMapping
    public void addUser(User user){
       userService.addUser(user);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable int id){
        userService.deleteUser(id);
    }
    @PutMapping("{id}")
    public void updateUser(@PathVariable int id, String name){
        userService.updateUser(id, name);
    }
}
