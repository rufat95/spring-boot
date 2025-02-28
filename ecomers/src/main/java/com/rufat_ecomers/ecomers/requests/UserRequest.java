package com.rufat_ecomers.ecomers.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    @NotNull(message = "Username can not be null")
    @NotEmpty(message = "Username can not be Empty")
    @NotBlank(message = "Username can not contain blanks")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*]).{8,}$",
            message = "Password must contain at least one big letter, number and character.")
    private String password;

    @Email(message = "Invalid email format.")
    private String email;
}
