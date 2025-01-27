package az.supertodo.Todo.services;

import az.supertodo.Todo.mappers.UserMapper;
import az.supertodo.Todo.requests.UserLoginRequest;
import az.supertodo.Todo.responses.UserLoginResponse;
import az.supertodo.Todo.usefull.SuccessResult;
import az.supertodo.Todo.entities.User;
import az.supertodo.Todo.repositories.UserRepo;
import az.supertodo.Todo.repositories.UserRepository;
import az.supertodo.Todo.requests.UserCreateRequest;
import az.supertodo.Todo.requests.UserUpdateRequest;
import az.supertodo.Todo.responses.UserCreateResponse;
import az.supertodo.Todo.responses.UserUpdateResponse;
import az.supertodo.Todo.usefull.UseFullFunctions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UseFullFunctions useFullFunctions;
    private final UserRepo userRepo;

    // This function create new User.
    public UserCreateResponse createUser(UserCreateRequest userCreateRequest) {
        useFullFunctions.capitalizeWordRequest(userCreateRequest);
        User userRequest = UserMapper.createUserRequest(userCreateRequest);
        User user = userRepository.save(userRequest);
        return UserMapper.createUserResponse(user);
    }

    //This function update user elements.
    public UserUpdateResponse updateUser(Long userId, UserUpdateRequest userUpdateRequest) {
        User user = userRepository.findById(userId).orElseThrow(() ->
                new RuntimeException("That user not found in db."));
        user.setUsername(userUpdateRequest.getUsername().trim());
        user.setPassword(userUpdateRequest.getPassword());
        User updatedUser = userRepository.save(user);
        return UserMapper.updateUserResponse(updatedUser);
    }

    //This function delete by id user.
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    //This function get by name user or all users.
    private List<User> getOneUser(String userEmail){
        return userRepository.findAllByEmail(userEmail);
    }

    public List<User> getUsers(String userEmail) {
        if(userEmail != null){
            return this.getOneUser(userEmail);
        }
        return userRepository.findAll();
    }

    // This is log in function
    public SuccessResult<UserLoginResponse> logIn(UserLoginRequest userLoginRequest) {
        User user = userRepository.findByEmail(userLoginRequest.getEmail());
        if (user != null && (userLoginRequest.getEmail().equals(user.getEmail())) &&
                            (userLoginRequest.getPassword().equals(user.getPassword()))) {
            userRepo.setId(user.getId());
            System.out.println(userRepo.getId());

            UserLoginResponse userLoginResponse = new UserLoginResponse();
            userLoginResponse.setId(user.getId());
            userLoginResponse.setUsername(user.getUsername());
            userLoginResponse.setEmail(user.getEmail());

            return new SuccessResult<>(userLoginResponse, "Successfully log in.");
        } else {
            throw new RuntimeException("That user not found !");
        }
    }


}
