package az.candyshop.CandyShop.responses.OrderResponse;

import az.candyshop.CandyShop.enums.OrderStatus;
import az.candyshop.CandyShop.enums.PaymentStatus;
import az.candyshop.CandyShop.enums.PaymentType;
import az.candyshop.CandyShop.responses.OrderItemsResponse.OIResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long id;
    private String username;
    private String email;
    private String number;
    private String address;
    private BigDecimal totalAmount;
    private OrderStatus orderStatus;
    private PaymentType paymentType;
    private PaymentStatus paymentStatus;
    private List<OIResponse> oiResponseList;
    private LocalDateTime createTime;
}
