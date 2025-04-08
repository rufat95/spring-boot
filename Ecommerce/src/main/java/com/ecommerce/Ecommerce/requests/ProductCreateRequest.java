package com.ecommerce.Ecommerce.requests;

import com.ecommerce.Ecommerce.enums.ProductBulk;
import com.ecommerce.Ecommerce.enums.ProductCategory;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {

    @Size(min = 3, max = 100, message = "Product name must be min 3 and max 100 character !")
    @NotNull(message = "Product can not be null !")
    @NotEmpty(message = "Product can not be empty !")
    private String name;

    @Size(min = 1, max = 100, message = "Brand name must be min 3 and max 100 character !")
    @NotNull(message = "Brand can not be null !")
    @NotEmpty(message = "Brand can not be empty !")
    private String brand;

    @NotNull(message = "Stock can not be null !")
    @Digits(message="Stock should contain 4 digits and numeric format.", fraction = 0, integer = 4)
    private Integer stock;

    @NotEmpty(message = "Description can not be empty !")
    @NotNull(message = "Description can not be null !")
    private String description;

    @NotNull(message = "Sale price can not be null !")
    @Digits(message="Sale price should contain 4 digits and numeric format.",
            fraction = 2, integer = 3)
    private BigDecimal salePrice;

    @NotNull(message = "Purchase price can not be null !")
    @Digits(message="Purchase price should contain 4 digits and numeric format.",
            fraction = 2, integer = 3)
    private BigDecimal purchasePrice;

    @NotNull(message = "Category can not be null !")
    private ProductCategory productCategory;

    @NotNull(message = "Size can not be null !")
    private ProductBulk productBulk;
}
