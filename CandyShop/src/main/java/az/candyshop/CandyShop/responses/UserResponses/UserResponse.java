package az.candyshop.CandyShop.responses.UserResponses;

import az.candyshop.CandyShop.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String number;
    private UserRole userRole;
    private String userImage;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
