package com.example.Practice.worthless;

import org.springframework.stereotype.Component;

@Component
public class WorthlessFunctions {
    public String capitalizeWord(String word){
        char firstLetter = word.charAt(0);
        char bigFirstLatter = Character.toUpperCase(firstLetter);
        String username;
        username = bigFirstLatter + word.substring(1);
        return username;
    }

    public boolean isValidEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }

    public boolean isValidPassword(String password) {
        return password.matches("^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*]).{8,}$");
    }


}
