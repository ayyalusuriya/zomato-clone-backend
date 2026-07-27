package com.zomato.repository;

import com.zomato.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {

    List<Food> findByRestaurantId(Long restaurantId);

    List<Food> findByCategoryId(Long categoryId);

    List<Food> findByFoodNameContainingIgnoreCase(String foodName);

}