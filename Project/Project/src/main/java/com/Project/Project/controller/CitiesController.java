package com.Project.Project.controller;

import com.Project.Project.models.Cities;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/cities")
public class CitiesController {

    @GetMapping
    public ResponseEntity<List<Cities>> getCities(){
        Cities baku = new Cities("99", "Baku");
        Cities kurdemir = new Cities("33", "Kurdemir");

        List<Cities> cities = Arrays.asList(baku, kurdemir);
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }
}
