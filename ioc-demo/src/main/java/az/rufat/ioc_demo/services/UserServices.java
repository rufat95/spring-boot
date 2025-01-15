package az.rufat.ioc_demo.services;

import az.rufat.ioc_demo.entities.User;
import az.rufat.ioc_demo.repositories.UserRepositories;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServices {

    private final UserRepositories userRepositories;

    public List<User> findAll(){
        return this.userRepositories.getUsers().
                stream().
                peek(user -> user.setUsername("Dear " + user.getUsername())).toList();
    }

    public void save(User user){
        this.userRepositories.save(user);
    }
}
