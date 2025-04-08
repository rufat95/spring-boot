package com.file.file.client.request;

import lombok.*;

import java.math.BigDecimal;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RateRequest {
    private String localDateTime;
    private String base;
    private Map<String, BigDecimal> rates;
}
