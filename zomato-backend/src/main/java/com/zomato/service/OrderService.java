package com.zomato.service;

import com.zomato.dto.OrderResponse;
import com.zomato.dto.PlaceOrderRequest;
import com.zomato.enums.OrderStatus;

import java.util.List;

public interface OrderService {

    OrderResponse placeOrder(PlaceOrderRequest request);

    List<OrderResponse> getUserOrders(Long userId);

    OrderResponse getOrder(Long orderId);

    OrderResponse updateStatus(Long orderId, OrderStatus status);

    void cancelOrder(Long orderId);

}