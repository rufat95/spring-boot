package az.candyshop.CandyShop.requests.UserRequests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPassRequest {
    @NotNull(message = "Password can not be null !")
    @NotEmpty(message = "Password can not be empty !")
    @NotBlank(message = "Password can not contain blank !")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&+=*]).{8,}$",
            message = "Password must contain at least one big letter, number and character.")
    private String newPassword;
}
