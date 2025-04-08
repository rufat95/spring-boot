package com.ecommerce.Ecommerce.resposes;

import com.ecommerce.Ecommerce.enums.ProductBulk;
import com.ecommerce.Ecommerce.enums.ProductCategory;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductResponse {
    private String name;
    private String brand;
    private Integer stock;
    private String description;
    private BigDecimal salePrice;
    private BigDecimal purchasePrice;
    private ProductCategory productCategory;
    private ProductBulk productBulk;
}
