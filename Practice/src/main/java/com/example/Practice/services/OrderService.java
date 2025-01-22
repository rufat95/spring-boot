package com.example.Practice.services;

import com.example.Practice.entities.Order;
import com.example.Practice.repos.OrderRepositories;
import com.example.Practice.repos.UserRepos;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Service
@AllArgsConstructor
@CrossOrigin
public class OrderService {
    private OrderRepositories orderRepositories;
    private UserRepos userRepos;

    public void save(Order order){
        order.setUser_id(userRepos.getUserId());
        this.orderRepositories.save(order);
    }

    // This function get orders or one order by price
    private List<Order> findByPrice(Double price){
        return orderRepositories.findByPrice(price);
    }

    public List<Order> getOrders(Double price){
        if(price != null){
            return this.findByPrice(price);
        }
        return orderRepositories.findAll();
    }

}
