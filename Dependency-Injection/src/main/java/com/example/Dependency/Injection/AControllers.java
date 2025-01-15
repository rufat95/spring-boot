package com.example.Dependency.Injection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


public class AControllers {

    // Fields injection
    @Autowired
    private FirstClass firstClass;
    private SecondClass secondClass;
    private final ThirdClass thirdClass;

    // Constructor injection
    @Autowired
    public AControllers(ThirdClass thirdClass) {
        this.thirdClass = thirdClass;
    }

    @GetMapping("/names")
    public String getFirstClass() {
        return this.firstClass.getName() + " - " +
                this.secondClass.getName() + " - " +
                this.thirdClass.getName();
    }

    //Setter injection
    @Autowired
    public void setSecondClass(SecondClass secondClass) {
        this.secondClass = secondClass;
    }
}
