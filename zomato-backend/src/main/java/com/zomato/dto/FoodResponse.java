package com.zomato.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FoodResponse {

    private Long id;
    private String foodName;
    private String description;
    private Double price;
    private String imageUrl;
    private Boolean veg;
    private Boolean available;
    private Double rating;

    private Long restaurantId;
    private String restaurantName;

    private Long categoryId;
    private String categoryName;

}