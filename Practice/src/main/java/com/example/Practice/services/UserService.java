package com.example.Practice.services;

import com.example.Practice.entities.User;
import com.example.Practice.repos.UserRepos;
import com.example.Practice.repos.UserRepositories;
import com.example.Practice.worthless.WorthlessFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final WorthlessFunctions worthlessFunctions;
    private final UserRepositories userRepositories;
    private final UserRepos userRepos;

    // This function get all users
    public List<User> findAll(){
        return this.userRepositories.findAll();
    }
    // This function get one user by id
    public Optional<User> getOneUser(Integer userId){
        return this.userRepositories.findById(userId);
    }
    // This function save new user
    public void save(User user){
        user.setUsername(worthlessFunctions.capitalizeWord(user.getUsername()));
        if(userRepositories.existsByUsername(user.getUsername()))
            throw new RuntimeException("This username exist");
        if(!worthlessFunctions.isValidPassword(user.getPassword()))
            throw new RuntimeException("Password in not valid");

        this.userRepositories.save(user);
    }
    // This function update one user
    public User updateOneUser(Integer user_id, User newUser) {
        Optional<User> user = this.userRepositories.findById(user_id);

        if(user.isPresent()){
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setPassword(newUser.getPassword());
            this.userRepositories.save(foundUser);
            return foundUser;
        }else {
            return null;
        }
    }
    // This function delete one user
    public void deleteUser(Integer userId){
        this.userRepositories.deleteById(userId);
    }
    // This is log in function
    public void logIn(User user) {
        User user1 = userRepositories.findByUsername(user.getUsername());
        if (user1 != null && user.getUsername().equals(user1.getUsername())) {
            userRepos.setUserId(user1.getUser_id());
            System.out.println(userRepos.getUserId());
        } else {
            System.out.println("User can not found.");
        }
    }


}
