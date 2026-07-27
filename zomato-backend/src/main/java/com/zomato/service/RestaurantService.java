package com.zomato.service;

import com.zomato.dto.RestaurantRequest;
import com.zomato.dto.RestaurantResponse;

import java.util.List;

public interface RestaurantService {

    RestaurantResponse addRestaurant(RestaurantRequest request);

    List<RestaurantResponse> getAllRestaurants();

    RestaurantResponse getRestaurantById(Long id);

    RestaurantResponse updateRestaurant(Long id, RestaurantRequest request);

    void deleteRestaurant(Long id);

    List<RestaurantResponse> searchRestaurant(String name);

}