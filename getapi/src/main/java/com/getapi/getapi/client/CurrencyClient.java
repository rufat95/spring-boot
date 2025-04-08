package com.getapi.getapi.client;

import com.getapi.getapi.client.request.RateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "rate-service", url = "https://api.currencyfreaks.com")
public interface CurrencyClient {

    @GetMapping("/v2.0/rates/latest")
    RateRequest getExchangeRate(@RequestParam String apikey);
}
