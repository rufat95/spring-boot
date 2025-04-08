package az.candyshop.CandyShop.controllers;

import az.candyshop.CandyShop.entities.OrderItems;
import az.candyshop.CandyShop.responses.OrderItemsResponse.OIResponse;
import az.candyshop.CandyShop.services.OIService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/orderItems")
public class OIControllers {
    private final OIService oiService;

    @GetMapping("/get_order_items_by_order_id")
    public List<OIResponse> getOrderItemsByOrderId(
            @RequestParam Long id){
        return oiService.getOrderItemsByOrderId(id);
    }
}
