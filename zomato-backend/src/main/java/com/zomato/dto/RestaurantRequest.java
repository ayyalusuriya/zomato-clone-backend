package com.zomato.dto;

import lombok.Data;

@Data
public class RestaurantRequest {

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
    private Long ownerId;

}