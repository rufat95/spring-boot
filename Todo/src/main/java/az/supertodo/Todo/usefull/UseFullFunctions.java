package az.supertodo.Todo.usefull;

import az.supertodo.Todo.requests.UserCreateRequest;
import az.supertodo.Todo.requests.UserUpdateRequest;
import org.springframework.stereotype.Component;

@Component
public class UseFullFunctions {

    public void capitalizeWordRequest(UserCreateRequest userCreateRequest){
        String username = capitalize(userCreateRequest.getUsername());
        userCreateRequest.setUsername(username);
    }

    public void capitalizeWordResponse(UserUpdateRequest userUpdateRequest){
        String username = capitalize(userUpdateRequest.getUsername());
        userUpdateRequest.setUsername(username);
    }


    private String capitalize(String username){
        char firstLetter = username.charAt(0);
        char bigFirstLatter = Character.toUpperCase(firstLetter);

        return bigFirstLatter + username.substring(1);
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*]).{8,}$");
    }


}
