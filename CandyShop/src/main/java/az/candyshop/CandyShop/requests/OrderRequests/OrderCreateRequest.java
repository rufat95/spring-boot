package az.candyshop.CandyShop.requests.OrderRequests;

import az.candyshop.CandyShop.enums.PaymentType;
import az.candyshop.CandyShop.requests.OrederItemsRequest.OIRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    private Long userId;

    @NotNull(message = "Address can not be null.")
    @NotEmpty(message = "Address can not be empty.")
    @NotBlank(message = "Address can not contain blank.")
    private String address;

    private PaymentType paymentType;

    private List<OIRequest> products;
}
