package com.example.Dependency.Injection;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class BControllers {
    private FirstClass firstClass;
    private SecondClass secondClass;
    private ThirdClass thirdClass;

    @Autowired
    public BControllers(ThirdClass thirdClass, FirstClass firstClass, SecondClass secondClass) {
        this.thirdClass = thirdClass;
        this.firstClass = firstClass;
        this.secondClass = secondClass;
    }

    @GetMapping("/names")
    public String getFirstClass() {
        return this.firstClass.getName() + " - " +
                this.secondClass.getName() + " - " +
                this.thirdClass.getName();
    }
}
