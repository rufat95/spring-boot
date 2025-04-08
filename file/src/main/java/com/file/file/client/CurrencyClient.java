package com.file.file.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


public interface CurrencyClient {
    @GetMapping
    Object getExchangeRate(@RequestParam String apikey);
}
