package com.zomato.service;

import com.zomato.dto.FoodRequest;
import com.zomato.dto.FoodResponse;

import java.util.List;

public interface FoodService {

    FoodResponse addFood(FoodRequest request);

    List<FoodResponse> getAllFoods();

    FoodResponse getFoodById(Long id);

    FoodResponse updateFood(Long id, FoodRequest request);

    void deleteFood(Long id);

    List<FoodResponse> getFoodsByRestaurant(Long restaurantId);

    List<FoodResponse> getFoodsByCategory(Long categoryId);

    List<FoodResponse> searchFood(String name);

}