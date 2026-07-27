package com.zomato.dto;

import lombok.Data;

@Data
public class CartRequest {

    private Long userId;

    private Long foodId;

    private Integer quantity;

}