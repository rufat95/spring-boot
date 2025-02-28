package com.ecommerce.Ecommerce.resposes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {
    private String username;
    private String number;
    private String wpNumber;
}
