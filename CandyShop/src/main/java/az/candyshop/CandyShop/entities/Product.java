package az.candyshop.CandyShop.entities;

import az.candyshop.CandyShop.enums.ProductBulk;
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

    @Column(name = "stock")
    private Integer stock;

    @Column(name = "bulk")
    @Enumerated(EnumType.STRING)
    private ProductBulk productBulk;

    @Column(name = "selling_price")
    private BigDecimal sellingPrice;

    @Column(name = "origin_price")
    private BigDecimal originPrice;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "category")
    private String category;

    @Column(name = "picture")
    private String picture;

    @Column(name = "video")
    private String video;

    @CreationTimestamp
    private LocalDateTime createTime;

    @UpdateTimestamp
    private LocalDateTime updateTime;
}
