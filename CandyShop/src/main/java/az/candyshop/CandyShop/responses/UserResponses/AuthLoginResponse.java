package az.candyshop.CandyShop.responses.UserResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthLoginResponse {
    private Long id;
    private String username;
    private String email;
    private String number;
    private String token;
}
