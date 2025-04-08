package com.getapi.getapi.controllers;

import com.getapi.getapi.client.request.RateRequest;
import com.getapi.getapi.services.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/currency")
public class CurrencyController {
    private final CurrencyService currencyService;

    @GetMapping("/rate")
    public RateRequest getExchangeRate(){
        return currencyService.getExchangeRate();
    }
}
