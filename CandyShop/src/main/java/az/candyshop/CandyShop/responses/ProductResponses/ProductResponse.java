package az.candyshop.CandyShop.responses.ProductResponses;

import az.candyshop.CandyShop.enums.ProductBulk;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String name;
    private Integer stock;
    private ProductBulk productBulk;
    private BigDecimal sellingPrice;
    private String description;
    private String category;
    private String picture;
    private String video;
}
