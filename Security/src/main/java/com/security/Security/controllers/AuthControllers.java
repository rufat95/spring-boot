package com.security.Security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class AuthControllers {

    @GetMapping("/auth/login")
    public String index(){
        return "This is login page !";
    }

    @GetMapping("/auth/register")
    public String dashboard(){
        return "This is register page !";
    }
}
