package com.zomato.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantResponse {

    private Long id;
    private String restaurantName;
    private String description;
    private String address;
    private String city;
    private String state;
    private String pincode;
    private String phone;
    private String email;
    private String openingTime;
    private String closingTime;
    private String imageUrl;
    private Double rating;
    private Boolean active;

    private Long ownerId;
    private String ownerName;

}