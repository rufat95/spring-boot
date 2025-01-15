package com.example.Practice.controllers;

import com.example.Practice.entities.Order;
import com.example.Practice.services.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private OrderService orderService;

    @GetMapping
    public List<Order> getOrders(@RequestParam(required = false) Double price){
        return this.orderService.getOrders(price);
    }

    @PostMapping
    public void save(Order order){
        this.orderService.save(order);
    }



}
