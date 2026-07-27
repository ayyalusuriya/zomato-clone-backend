package com.zomato.dto;

import com.zomato.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderResponse {

    private Long orderId;

    private Long userId;

    private String userName;

    private Double totalAmount;

    private OrderStatus status;

    private LocalDateTime orderDate;

}