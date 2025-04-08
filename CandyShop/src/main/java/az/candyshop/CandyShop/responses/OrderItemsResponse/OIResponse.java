package az.candyshop.CandyShop.responses.OrderItemsResponse;

import az.candyshop.CandyShop.enums.ProductBulk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OIResponse {
    private Long id;
    private String productName;
    private Integer quantity;
    private ProductBulk productBulk;
    private BigDecimal unitePrice;
    private BigDecimal subtotal;
}
