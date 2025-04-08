package com.getapi.getapi.client.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Map;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {
    private String date;
    private String base;
    private Map<String, BigDecimal> rates;
}
