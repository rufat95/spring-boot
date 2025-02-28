package az.texnoera.texnoera.requests;
import az.texnoera.texnoera.enums.UserRoles;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TeacherRequest {
    @NotNull(message = "Salary can not be null")
    @Min(value = 400, message = "The salary cannot be less than 400.")
    private Integer salary;

    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Lastname can not be null")
    @NotEmpty(message = "Lastname can not be Empty")
    @NotBlank(message = "Lastname can not contain blanks")
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
