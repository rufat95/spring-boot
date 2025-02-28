package com.ecommerce.Ecommerce.repositories;

import com.ecommerce.Ecommerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
