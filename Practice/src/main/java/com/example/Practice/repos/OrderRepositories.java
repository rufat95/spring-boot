package com.example.Practice.repos;

import com.example.Practice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepositories extends JpaRepository<Order, Integer> {
    List<Order> findByPrice(Double price);
}
