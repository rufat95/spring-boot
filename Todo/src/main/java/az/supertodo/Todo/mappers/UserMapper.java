package az.supertodo.Todo.mappers;


import az.supertodo.Todo.entities.User;
import az.supertodo.Todo.requests.UserCreateRequest;
import az.supertodo.Todo.responses.UserCreateResponse;
import az.supertodo.Todo.responses.UserUpdateResponse;

public interface UserMapper {
    static User createUserRequest(UserCreateRequest userCreateRequest){
        return User.builder()
                .username(userCreateRequest.getUsername())
                .email(userCreateRequest.getEmail())
                .password(userCreateRequest.getPassword())
                .build();
    }

    static UserCreateResponse createUserResponse(User user){
        return UserCreateResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .build();
    }

    static UserUpdateResponse updateUserResponse(User user){
        return UserUpdateResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
    }
}
