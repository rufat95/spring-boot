package com.ecommerce.Ecommerce.resposes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImageResponse {
    private String username;
    private String password;
    private String number;
    private String wpNumber;
    private String profilePicture;
}
