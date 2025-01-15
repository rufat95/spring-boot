package az.rufat.ioc_container.services;

import az.rufat.ioc_container.entities.User;
import az.rufat.ioc_container.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll(){
        return this.userRepository.getUsers().
                stream().
                peek(u -> u.setUsername("Dear " + u.getUsername())).toList();
    }

    public void save(User user){
        if(user.getUsername().equals("Rufat")){
            throw new RuntimeException("Rufat can not be !");
        }
        this.userRepository.save(user);
    }
}
