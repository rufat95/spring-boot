package az.candyshop.CandyShop.requests.OrederItemsRequest;

import az.candyshop.CandyShop.enums.ProductBulk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OIRequest {
    private Long id;
    private Integer quantity;
    private ProductBulk productBulk;
}
