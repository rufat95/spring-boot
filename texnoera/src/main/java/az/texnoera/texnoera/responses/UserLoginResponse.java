package az.texnoera.texnoera.responses;

import az.texnoera.texnoera.enums.UserRoles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private UserRoles status;
}
