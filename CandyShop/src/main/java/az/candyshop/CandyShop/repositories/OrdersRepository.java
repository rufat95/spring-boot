package az.candyshop.CandyShop.repositories;

import az.candyshop.CandyShop.entities.Orders;
import az.candyshop.CandyShop.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long id);
    List<Orders> findByOrderStatus(OrderStatus orderStatus);
}
