package az.candyshop.CandyShop.responses.UserResponses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImageResponse {
    private String username;
    private String email;
    private String number;
    private String userImage;
}
