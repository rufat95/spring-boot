package com.file.file.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    @Pattern(regexp = "^\\S*$", message = "Username can not contain blank character !")
    @Size(min = 3, max = 20, message = "Username must be min 3 and max 20 character !")
    @NotNull(message = "Username can not be null !")
    @NotEmpty(message = "Username can not be empty !")
    @NotBlank(message = "Username can not contain blank !")
    private String username;

    @NotNull(message = "Password can not be null !")
    @NotEmpty(message = "Password can not be empty !")
    @NotBlank(message = "Password can not contain blank !")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=*]).{8,}$",
            message = "Password must contain at least one big letter, number and character.")
    private String password;
}
