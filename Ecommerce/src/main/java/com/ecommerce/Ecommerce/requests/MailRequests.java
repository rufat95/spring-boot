package com.ecommerce.Ecommerce.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MailRequests {
    private String to;
    private String subject;
    private String body;
}
