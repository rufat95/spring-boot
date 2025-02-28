package com.ecommerce.Ecommerce.requests;

import com.ecommerce.Ecommerce.entities.User;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEmailRequest {
    @Email(message = "Email not valid")
    private String email;
}
