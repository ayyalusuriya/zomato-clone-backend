package com.zomato.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CartResponse {

    private Long id;

    private Long userId;

    private String userName;

    private Long foodId;

    private String foodName;

    private Integer quantity;

    private Double price;

    private Double totalPrice;

}