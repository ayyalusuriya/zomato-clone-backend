package com.zomato.service.impl;

import com.zomato.dto.OrderResponse;
import com.zomato.dto.PlaceOrderRequest;
import com.zomato.entity.Cart;
import com.zomato.entity.Order;
import com.zomato.entity.OrderItem;
import com.zomato.entity.User;
import com.zomato.enums.CartStatus;
import com.zomato.enums.OrderStatus;
import com.zomato.repository.CartRepository;
import com.zomato.repository.OrderItemRepository;
import com.zomato.repository.OrderRepository;
import com.zomato.repository.UserRepository;
import com.zomato.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final CartRepository cartRepository;
    private final UserRepository userRepository;

    @Override
    public OrderResponse placeOrder(PlaceOrderRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<Cart> cartItems =
                cartRepository.findByUserIdAndStatus(user.getId(), CartStatus.ACTIVE);

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        double total = cartItems.stream()
                .mapToDouble(Cart::getTotalPrice)
                .sum();

        Order order = Order.builder()
                .user(user)
                .totalAmount(total)
                .status(OrderStatus.PLACED)
                .build();

        order = orderRepository.save(order);

        List<OrderItem> orderItems = new ArrayList<>();

        for (Cart cart : cartItems) {

            OrderItem item = OrderItem.builder()
                    .order(order)
                    .food(cart.getFood())
                    .quantity(cart.getQuantity())
                    .price(cart.getPrice())
                    .totalPrice(cart.getTotalPrice())
                    .build();

            orderItems.add(orderItemRepository.save(item));

            cart.setStatus(CartStatus.ORDERED);
            cartRepository.save(cart);
        }

        order.setOrderItems(orderItems);
        orderRepository.save(order);

        return map(order);
    }

    @Override
    public List<OrderResponse> getUserOrders(Long userId) {

        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public OrderResponse getOrder(Long orderId) {

        return map(orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found")));
    }

    @Override
    public OrderResponse updateStatus(Long orderId,
                                      OrderStatus status) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(status);

        return map(orderRepository.save(order));
    }

    @Override
    public void cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        order.setStatus(OrderStatus.CANCELLED);

        orderRepository.save(order);
    }

    private OrderResponse map(Order order) {

        return OrderResponse.builder()
                .orderId(order.getId())
                .userId(order.getUser().getId())
                .userName(order.getUser().getFullName())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .orderDate(order.getOrderDate())
                .build();
    }
}