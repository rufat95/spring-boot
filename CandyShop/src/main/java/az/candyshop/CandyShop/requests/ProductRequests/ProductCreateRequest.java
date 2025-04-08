package az.candyshop.CandyShop.requests.ProductRequests;

import az.candyshop.CandyShop.enums.ProductBulk;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @NotNull(message = "Product can not be null !")
    @NotEmpty(message = "Product can not be empty !")
    private String name;

    @NotNull(message = "Stock can not be null !")
    @Digits(message="Stock should contain 4 digits and numeric format.", fraction = 0, integer = 4)
    private Integer stock;

    @NotNull(message = "Bulk can not be null !")
    private ProductBulk productBulk;

    @NotNull(message = "Selling price can not be null !")
    @Digits(message="Selling price should contain 4 digits and numeric format.",
            fraction = 2, integer = 3)
    private BigDecimal sellingPrice;

    @NotNull(message = "Origin price can not be null !")
    @Digits(message="Origin price should contain 4 digits and numeric format.",
            fraction = 2, integer = 3)
    private BigDecimal originPrice;

    @NotEmpty(message = "Description can not be empty !")
    @NotNull(message = "Description can not be null !")
    private String description;

    @NotNull(message = "Category can not be null !")
    @NotEmpty(message = "Category can not be empty !")
    @NotBlank(message = "Category can not be blank !")
    private String category;
}
