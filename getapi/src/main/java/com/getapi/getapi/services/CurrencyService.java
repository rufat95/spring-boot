package com.getapi.getapi.services;

import com.getapi.getapi.client.CurrencyClient;
import com.getapi.getapi.client.request.RateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyClient currencyClient;

    public RateRequest getExchangeRate(){
        String apikey = "9526a08915f44eafb2ac857d97af16de";
        RateRequest exchangeRate = currencyClient.getExchangeRate(apikey);
        log.info(exchangeRate);
        return exchangeRate;
    }
}
