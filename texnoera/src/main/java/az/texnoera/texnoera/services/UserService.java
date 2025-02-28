package az.texnoera.texnoera.services;

import az.texnoera.texnoera.entities.User;
import az.texnoera.texnoera.enums.StatusCode;
import az.texnoera.texnoera.exceptions.BaseException;
import az.texnoera.texnoera.repositories.UserRepository;
import az.texnoera.texnoera.requests.UserLoginRequest;
import az.texnoera.texnoera.requests.UserRequest;
import az.texnoera.texnoera.responses.UserLoginResponse;
import az.texnoera.texnoera.utils.result.DataResult;
import az.texnoera.texnoera.utils.result.Result;
import az.texnoera.texnoera.utils.result.SuccessDataResult;
import az.texnoera.texnoera.utils.result.SuccessResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(UserRequest userRequest) {
        User user = new User();

        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setUserRoles(userRequest.getUserRoles());

        return userRepository.save(user);
    }

    public User getOneUserByEmail(String userEmail) {
        return userRepository.findUserByEmail(userEmail)
                .orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND));
    }

    public List<User> findUsersByFirstName(String firstName) {
        List<User> user = userRepository.findByFirstNameStartingWith(firstName);

        if(user.isEmpty()){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND);
        }
        return user;
    }

    public User updateUser(Long userId, UserRequest userRequest) {
        User user = userRepository.findById(userId).
                orElseThrow(() -> new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND));

        user.setEmail(userRequest.getEmail());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setPassword(userRequest.getPassword());
        user.setUserRoles(userRequest.getUserRoles());

        return userRepository.save(user);
    }

    public Result deleteUser(Long userId) {
        userRepository.deleteById(userId);

        return new SuccessResult("Successfully deleted");
    }

    public DataResult<UserLoginResponse> login(@Valid UserLoginRequest userLoginRequest) {
        User user = userRepository.findUserByEmailAndPassword(
                userLoginRequest.getEmail(), userLoginRequest.getPassword());

        if(user != null && (userLoginRequest.getEmail().equals(user.getEmail()) &&
                            userLoginRequest.getPassword().equals(user.getPassword()))) {

            UserLoginResponse userLoginResponse = new UserLoginResponse();
            userLoginResponse.setId(user.getId());
            userLoginResponse.setFirstName(user.getFirstName());
            userLoginResponse.setLastName(user.getLastName());
            userLoginResponse.setEmail(user.getEmail());
            userLoginResponse.setStatus(user.getUserRoles());

            return new SuccessDataResult<>("Log in successfully", userLoginResponse);
        }
        throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.USER_NOT_LOGIN);
    }
}
