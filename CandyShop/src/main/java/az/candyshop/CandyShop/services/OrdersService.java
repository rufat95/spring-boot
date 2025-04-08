package az.candyshop.CandyShop.services;

import az.candyshop.CandyShop.entities.OrderItems;
import az.candyshop.CandyShop.entities.Orders;
import az.candyshop.CandyShop.entities.Product;
import az.candyshop.CandyShop.entities.User;
import az.candyshop.CandyShop.enums.*;
import az.candyshop.CandyShop.repositories.OrderItemsRepository;
import az.candyshop.CandyShop.repositories.OrdersRepository;
import az.candyshop.CandyShop.repositories.ProductRepository;
import az.candyshop.CandyShop.repositories.UserRepository;
import az.candyshop.CandyShop.requests.OrderRequests.OrderCreateRequest;
import az.candyshop.CandyShop.requests.OrederItemsRequest.OIRequest;
import az.candyshop.CandyShop.responses.OrderItemsResponse.OIResponse;
import az.candyshop.CandyShop.responses.OrderResponse.OrderResponse;
import az.candyshop.CandyShop.result.exception.BaseException;
import az.candyshop.CandyShop.result.success.SuccessDataResult;
import az.candyshop.CandyShop.result.success.SuccessResult;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final OIService oiService;

    // Put api for cancel order
    public SuccessResult cancelOrder(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCode.ORDER_NOT_FOUND));
        if(order.getOrderStatus() == OrderStatus.PENDING){
            order.setOrderStatus(OrderStatus.OFF);
            order.setPaymentStatus(PaymentStatus.UNPAID);
            ordersRepository.save(order);
        }else{
            throw new BaseException(HttpStatus.BAD_REQUEST, StatusCode.ORDER_OFF);
        }
        return new SuccessResult("Order cancel unfortunately.");
    }

    // Put api for update order status
    public SuccessResult updateOrderStatus(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCode.ORDER_NOT_FOUND));
        List<OrderItems> orderItems = orderItemsRepository.findByOrderId(order.getId());

        orderItems.forEach(item ->{
            Product product = productRepository.findById(item.getProduct().getId()).orElseThrow(() ->
                    new BaseException(HttpStatus.NOT_FOUND, StatusCode.PRODUCT_NOT_FOUND));

            Integer newStock = product.getStock() - item.getQuantity();
            product.setStock(newStock);
            productRepository.save(product);
        });

        order.setOrderStatus(OrderStatus.ARRIVED);
        order.setPaymentStatus(PaymentStatus.PAID);
        ordersRepository.save(order);
        return new SuccessResult("Order arrived successfully");
    }

    // Get api for some order by user id
    public SuccessDataResult<List<OrderResponse>> getOrdersByUserId(Long userId) {
        List<Orders> orders = ordersRepository.findByUserId(userId);
        if (orders == null){
            throw new BaseException(HttpStatus.NOT_FOUND, StatusCode.ORDER_NOT_FOUND);
        }

        List<OrderResponse> orderResponses = orders.stream().map(item ->{
            User user = userRepository.findById(item.getUser().getId()).orElseThrow(() ->
                    new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND));

            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(item.getId());
            orderResponse.setUsername(user.getUsername());
            orderResponse.setEmail(user.getEmail());
            orderResponse.setNumber(user.getNumber());
            orderResponse.setAddress(item.getAddress());
            orderResponse.setTotalAmount(item.getTotalAmount());
            orderResponse.setOrderStatus(item.getOrderStatus());
            orderResponse.setPaymentType(item.getPaymentType());
            orderResponse.setPaymentStatus(item.getPaymentStatus());
            List<OIResponse> oiResponseList = oiService.getOrderItemsByOrderId(item.getId());
            orderResponse.setOiResponseList(oiResponseList);
            orderResponse.setCreateTime(item.getCreateTime());
            return orderResponse;
        }).toList();

        return new SuccessDataResult<>("Orders fetched successfully",  orderResponses);
    }

    // Get api by order id
    public SuccessDataResult<OrderResponse> getOrderById(Long id) {
        Orders order = ordersRepository.findById(id).orElseThrow(() ->
            new BaseException(HttpStatus.NOT_FOUND, StatusCode.ORDER_NOT_FOUND));
        User user = userRepository.findById(order.getUser().getId()).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_NOT_FOUND));

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setId(order.getId());
        orderResponse.setUsername(user.getUsername());
        orderResponse.setEmail(user.getEmail());
        orderResponse.setNumber(user.getNumber());
        orderResponse.setAddress(order.getAddress());
        orderResponse.setTotalAmount(order.getTotalAmount());
        orderResponse.setOrderStatus(order.getOrderStatus());
        orderResponse.setPaymentType(order.getPaymentType());
        orderResponse.setPaymentStatus(order.getPaymentStatus());
        List<OIResponse> oiResponseList = oiService.getOrderItemsByOrderId(order.getId());
        orderResponse.setOiResponseList(oiResponseList);
        orderResponse.setCreateTime(order.getCreateTime());

        return new SuccessDataResult<>("Order fetched successfully",  orderResponse);
    }

    // Post api for create new order
    @Transactional
    public SuccessResult createOrder(OrderCreateRequest orderCreateRequest) {
        User user = userRepository.findById(orderCreateRequest.getUserId()).orElseThrow(() ->
                new BaseException(HttpStatus.NOT_FOUND, StatusCode.USER_LOG_IN));

        Orders orders = orderSave(orderCreateRequest, user);
        createOrderItems(orderCreateRequest, orders);

        return new SuccessResult("Order created successfully.");
    }

    private Orders orderSave(OrderCreateRequest orderCreateRequest, User user){
        Orders orders = new Orders();
        orders.setUser(user);
        orders.setTotalAmount(calculateTotalAmount(orderCreateRequest.getProducts()));
        orders.setAddress(orderCreateRequest.getAddress());
        orders.setPaymentType(orderCreateRequest.getPaymentType());
        orders.setOrderStatus(OrderStatus.PENDING);
        paymentTypeSet(orderCreateRequest, orders);
        return ordersRepository.save(orders);
    }

    private void createOrderItems(OrderCreateRequest orderCreateRequest,
                                  Orders savedOrder){
         orderCreateRequest.getProducts()
                .forEach(item -> {
                    Product product = productRepository.findById(item.getId())
                            .orElseThrow(() -> new BaseException(
                                    HttpStatus.NOT_FOUND, StatusCode.PRODUCT_NOT_FOUND));

                    OrderItems orderItem = new OrderItems();
                    orderItem.setOrder(savedOrder);
                    orderItem.setProduct(product);
                    orderItem.setQuantity(item.getQuantity());
                    orderItem.setProductBulk(item.getProductBulk());
                    orderItem.setUnitePrice(product.getSellingPrice());
                    calculateSubtotal(orderItem, product, item);

                    orderItemsRepository.save(orderItem);
                });
    }

    private void paymentTypeSet(OrderCreateRequest orderCreateRequest,
                                Orders orders){
        if(orderCreateRequest.getPaymentType() == PaymentType.CASH){
            orders.setPaymentStatus(PaymentStatus.UNPAID);
        }else {
            orders.setPaymentStatus(PaymentStatus.PAID);
        }
    }

    private void calculateSubtotal(OrderItems orderItem,
                                   Product product,
                                   OIRequest item){
        if(item.getProductBulk() == ProductBulk.PIECE ||
                item.getProductBulk() == ProductBulk.PACKET){
            BigDecimal subtotalPiece = product.getSellingPrice().multiply(
                    BigDecimal.valueOf(item.getQuantity()));
            orderItem.setSubtotal(subtotalPiece);
        } else if (item.getProductBulk() == ProductBulk.GRAM) {
            BigDecimal pricePerGram = product.getSellingPrice()
                    .divide(BigDecimal.valueOf(1000), 4, RoundingMode.HALF_UP);
            BigDecimal subtotalGram = pricePerGram.multiply(
                    BigDecimal.valueOf(item.getQuantity()));
            subtotalGram = subtotalGram.setScale(2, RoundingMode.HALF_UP);
            orderItem.setSubtotal(subtotalGram);
        }
    }

    private BigDecimal calculateTotalAmount(List<OIRequest> products) {
        BigDecimal total = BigDecimal.ZERO;
        for (OIRequest item : products) {
            Product product = productRepository.findById(item.getId())
                    .orElseThrow(() -> new BaseException(
                            HttpStatus.NOT_FOUND,
                            StatusCode.PRODUCT_NOT_FOUND));
            BigDecimal subtotal;
            if(item.getProductBulk() == ProductBulk.PIECE ||
                    item.getProductBulk() == ProductBulk.PACKET) {
                subtotal = product.getSellingPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()));
            } else if (item.getProductBulk() == ProductBulk.GRAM) {
                BigDecimal pricePerGram = product.getSellingPrice()
                        .divide(BigDecimal.valueOf(1000), 4, RoundingMode.HALF_UP);
                subtotal = pricePerGram.multiply(BigDecimal.valueOf(item.getQuantity()));
                subtotal = subtotal.setScale(2, RoundingMode.HALF_UP);
            } else {
                subtotal = product.getSellingPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity()));
            }
            total = total.add(subtotal);
        }
        return total;
    }
}
