package az.candyshop.CandyShop.requests.OrederItemsRequest;

import az.candyshop.CandyShop.enums.ProductBulk;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OIRequest {
    private Long id;
    @NotEmpty(message = "Quantity can not be empty !")
    @Min(value = 1, message = "Quantity must be at least 1")
    @Positive(message = "Quantity must be positive")
    private Integer quantity;

    @NotNull(message = "Bulk can bot be null.")
    @NotEmpty(message = "Bulk can not be empty.")
    private ProductBulk productBulk;
}
