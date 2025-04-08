package com.ecommerce.Ecommerce.entities;

import com.ecommerce.Ecommerce.enums.ProductBulk;
import com.ecommerce.Ecommerce.enums.ProductCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "stock")
    private Integer stock;

    @Column(columnDefinition = "text")
    private String description;

    @Column(name = "sale_price")
    private BigDecimal salePrice;

    @Column(name = "purchase_price")
    private BigDecimal purchasePrice;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private ProductCategory productCategory;

    @Column(name = "size")
    @Enumerated(EnumType.STRING)
    private ProductBulk productBulk;

    @Column(name = "image_1")
    private String image1;

    @Column(name = "image_2")
    private String image2;

    @Column(name = "image_3")
    private String image3;

    @Column(name = "image_4")
    private String image4;

    @Column(name = "image_5")
    private String image5;

    @Column(name = "product_video")
    private String video;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}