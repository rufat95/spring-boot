package com.security.Security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @GetMapping("/index")
    public String index(){
        return "This is user index page !";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "This is user dashboard page !";
    }
}
