package com.rufat.exception_management.controllers;

import com.rufat.exception_management.dto.DtoEmployee;
import com.rufat.exception_management.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/v1/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public DtoEmployee findEmployeeById(@PathVariable Long id){
        return employeeService.findEmployeeById(id);
    }
}
