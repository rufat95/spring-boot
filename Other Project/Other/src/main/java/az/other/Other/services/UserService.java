package az.other.Other.services;

import az.other.Other.entities.User;
import az.other.Other.repos.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User register(User newUser){
        return userRepository.save(newUser);
    }

    public User getOneUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }

    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);

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

    public void deleteOneUser(Long userId){
        this.userRepository.deleteById(userId);
    }
}
