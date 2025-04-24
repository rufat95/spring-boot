package az.candyshop.CandyShop.controllers;

import az.candyshop.CandyShop.entities.Orders;
import az.candyshop.CandyShop.enums.OrderStatus;
import az.candyshop.CandyShop.requests.OrderRequests.OrderCreateRequest;
import az.candyshop.CandyShop.responses.OrderResponse.OrderResponse;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessResult;
import az.candyshop.CandyShop.services.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orders")
public class OrdersController {
    private final OrdersService ordersService;

    @PutMapping("/cancel_order")
    public SuccessResult cancelOrder(@RequestParam Long id){
        return ordersService.cancelOrder(id);
    }

    @PutMapping("/update_order_status")
    public SuccessResult updateOrderStatus(@RequestParam Long id){
        return ordersService.updateOrderStatus(id);
    }

    @GetMapping("some_order_by_user_id")
    public SuccessDataResult<List<OrderResponse>> getOrdersByUserId(@RequestParam Long userId){
        return ordersService.getOrdersByUserId(userId);
    }

    @GetMapping("/one_order_by_order_id")
    public SuccessDataResult<OrderResponse> getOrderById(@RequestParam Long id){
        return ordersService.getOrderById(id);
    }

    @PostMapping("/create_order")
    public SuccessResult createOrder(
            @RequestBody @Valid OrderCreateRequest orderCreateRequest){
        return ordersService.createOrder(orderCreateRequest);
    }
}
