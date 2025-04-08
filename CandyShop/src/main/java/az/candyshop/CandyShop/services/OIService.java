package az.candyshop.CandyShop.services;

import az.candyshop.CandyShop.entities.OrderItems;
import az.candyshop.CandyShop.enums.StatusCode;
import az.candyshop.CandyShop.repositories.OrderItemsRepository;
import az.candyshop.CandyShop.responses.OrderItemsResponse.OIResponse;
import az.candyshop.CandyShop.result.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OIService {
    private final OrderItemsRepository orderItemsRepository;

    // Get api for get order items by order ig
    public List<OIResponse> getOrderItemsByOrderId(Long id) {
        List<OrderItems> orderItems = orderItemsRepository.findByOrderId(id);
        if (orderItems == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.ORDER_ITEMS_NOT_FOUND);
        }

        return orderItems.stream().map(item -> {
            OIResponse oiResponse = new OIResponse();
            oiResponse.setId(item.getId());
            oiResponse.setProductName(item.getProduct().getName());
            oiResponse.setQuantity(item.getQuantity());
            oiResponse.setProductBulk(item.getProductBulk());
            oiResponse.setUnitePrice(item.getUnitePrice());
            oiResponse.setSubtotal(item.getSubtotal());

            return oiResponse;
        }).toList();
    }
}
