package az.supertodo.Todo.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserCreateRequest {
    @NotNull(message = "Username is not be null.")
    @NotEmpty(message = "Username is not be empty.")
    @NotBlank(message = "Username does not contain blank.")
    private String username;

    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=*]).{8,}$",
            message = "Password must contain at least one big letter, number and character.")
    private String password;

    @Email(message = "Invalid email format.")
    private String email;
}
