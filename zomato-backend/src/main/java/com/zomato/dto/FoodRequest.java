package com.zomato.dto;

import lombok.Data;

@Data
public class FoodRequest {

    private String foodName;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean veg;
    private Boolean available;

    private Long restaurantId;
    private Long categoryId;

}