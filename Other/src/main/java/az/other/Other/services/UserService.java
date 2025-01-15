package az.other.Other.services;

import az.other.Other.entities.User;
import az.other.Other.repos.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers(){
        return this.userRepository.findAll();
    }

    public User register(User newUser){
        return this.userRepository.save(newUser);
    }

    public User getOneUser(Long user_id){
        return this.userRepository.findById(user_id).orElse(null);
    }


    public User updateOneUser(Long user_id, User newUser) {
        Optional<User> user = this.userRepository.findById(user_id);

        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            this.userRepository.save(foundUser);
            return foundUser;
        }else {
            return null;
        }
    }

    public void deleteOneUser(Long user_id){
        this.userRepository.deleteById(user_id);
    }
}
