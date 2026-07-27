package com.zomato.controller;

import com.zomato.dto.OrderResponse;
import com.zomato.dto.PlaceOrderRequest;
import com.zomato.enums.OrderStatus;
import com.zomato.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse placeOrder(
            @RequestBody PlaceOrderRequest request) {

        return orderService.placeOrder(request);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getUserOrders(
            @PathVariable Long userId) {

        return orderService.getUserOrders(userId);
    }

    @GetMapping("/{orderId}")
    public OrderResponse getOrder(
            @PathVariable Long orderId) {

        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}/status")
    public OrderResponse updateStatus(
            @PathVariable Long orderId,
            @RequestParam OrderStatus status) {

        return orderService.updateStatus(orderId, status);
    }

    @DeleteMapping("/{orderId}")
    public String cancelOrder(
            @PathVariable Long orderId) {

        orderService.cancelOrder(orderId);

        return "Order Cancelled Successfully";
    }
}