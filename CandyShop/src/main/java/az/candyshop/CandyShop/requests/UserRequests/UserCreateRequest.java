package az.candyshop.CandyShop.requests.UserRequests;

import az.candyshop.CandyShop.enums.UserRole;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequest {

    @Pattern(regexp = "^\\S*$", message = "Username can not contain blank character !")
    @Size(min = 3, max = 20, message = "Username must be min 3 and max 20 character !")
    @NotNull(message = "Username can not be null !")
    @NotEmpty(message = "Username can not be empty !")
    @NotBlank(message = "Username can not contain blank !")
    private String username;

    @Email(message = "Invalid email format.")
    @NotNull(message = "Email can not be null !")
    @NotEmpty(message = "Email can not be null !")
    @NotBlank(message = "Email can not contain blank !")
    private String email;

    @NotNull(message = "Password can not be null !")
    @NotEmpty(message = "Password can not be empty !")
    @NotBlank(message = "Password can not contain blank !")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=*]).{8,}$",
            message = "Password must contain at least one big letter, number and character.")
    private String password;

    @NotNull(message = "Number can not be null !")
    @NotEmpty(message = "Number can not be empty !")
    @NotBlank(message = "Number can not contain blank !")
    @Digits(message="Number should contain 10 digits and numeric format.", fraction = 0, integer = 10)
    @Size(min = 10, max = 10, message = "Number must contain 10 digits.")
    private String number;

    @NotNull(message = "Role can not be null !")
    private UserRole userRole;
}
