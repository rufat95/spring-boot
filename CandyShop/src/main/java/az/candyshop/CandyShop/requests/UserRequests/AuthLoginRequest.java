package az.candyshop.CandyShop.requests.UserRequests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginRequest {
    @Email(message = "Invalid email format.")
    @NotNull(message = "Email can not be null !")
    @NotEmpty(message = "Email can not be null !")
    @NotBlank(message = "Email can not contain blank !")
    private String email;

    @NotNull(message = "Password can not be null !")
    @NotEmpty(message = "Password can not be empty !")
    @NotBlank(message = "Password can not contain blank !")
    private String password;
}
