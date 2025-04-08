package com.security.Security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminControllers {
    @GetMapping("/index")
    public String index(){
        return "This is admin index page !";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "This is admin dashboard page !";
    }
}
