package az.candyshop.CandyShop.requests.OrderRequests;

import az.candyshop.CandyShop.enums.PaymentType;
import az.candyshop.CandyShop.requests.OrederItemsRequest.OIRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreateRequest {
    private Long userId;
    private String address;
    private PaymentType paymentType;
    private List<OIRequest> products;
}
