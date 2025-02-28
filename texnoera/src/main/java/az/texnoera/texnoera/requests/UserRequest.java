package az.texnoera.texnoera.requests;

import az.texnoera.texnoera.enums.UserRoles;
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
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Firstname can not be null")
    @NotEmpty(message = "Firstname can not be Empty")
    @NotBlank(message = "Firstname can not contain blanks")
    private String firstName;

    @NotNull(message = "Lastname can not be null")
    @NotEmpty(message = "Lastname can not be Empty")
    @NotBlank(message = "Lastname can not contain blanks")
    private String lastName;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*]).{8,}$",
            message = "Password must contain at least one big letter, number and character.")
    private String password;

    @NotNull(message = "Roles can not be null")
    private UserRoles userRoles;
}
