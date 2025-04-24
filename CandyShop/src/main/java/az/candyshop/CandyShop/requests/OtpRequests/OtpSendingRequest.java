package az.candyshop.CandyShop.requests.OtpRequests;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtpSendingRequest {
    @Email(message = "Invalid email format.")
    private String email;
}
