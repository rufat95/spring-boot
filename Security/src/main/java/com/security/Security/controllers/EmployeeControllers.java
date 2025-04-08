package com.security.Security.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employees")
public class EmployeeControllers {
    @GetMapping("/index")
    public String index(){
        return "This is employees index page !";
    }

    @GetMapping("/dashboard")
    public String dashboard(){
        return "This is employees dashboard page !";
    }
}
