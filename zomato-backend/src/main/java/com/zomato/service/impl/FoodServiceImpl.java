package com.zomato.service.impl;

import com.zomato.dto.FoodRequest;
import com.zomato.dto.FoodResponse;
import com.zomato.entity.Category;
import com.zomato.entity.Food;
import com.zomato.entity.Restaurant;
import com.zomato.repository.CategoryRepository;
import com.zomato.repository.FoodRepository;
import com.zomato.repository.RestaurantRepository;
import com.zomato.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodServiceImpl implements FoodService {

    private final FoodRepository foodRepository;
    private final RestaurantRepository restaurantRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public FoodResponse addFood(FoodRequest request) {

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Food food = Food.builder()
                .foodName(request.getFoodName())
                .description(request.getDescription())
                .price(request.getPrice())
                .imageUrl(request.getImageUrl())
                .veg(request.getVeg())
                .available(request.getAvailable())
                .rating(0.0)
                .restaurant(restaurant)
                .category(category)
                .build();

        return map(foodRepository.save(food));
    }

    @Override
    public List<FoodResponse> getAllFoods() {
        return foodRepository.findAll()
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public FoodResponse getFoodById(Long id) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        return map(food);
    }

    @Override
    public FoodResponse updateFood(Long id, FoodRequest request) {

        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        food.setFoodName(request.getFoodName());
        food.setDescription(request.getDescription());
        food.setPrice(request.getPrice());
        food.setImageUrl(request.getImageUrl());
        food.setVeg(request.getVeg());
        food.setAvailable(request.getAvailable());
        food.setRestaurant(restaurant);
        food.setCategory(category);

        food = foodRepository.save(food);

        return map(food);
    }

    @Override
    public void deleteFood(Long id) {

        if (!foodRepository.existsById(id)) {
            throw new RuntimeException("Food not found");
        }

        foodRepository.deleteById(id);
    }

    @Override
    public List<FoodResponse> getFoodsByRestaurant(Long restaurantId) {

        return foodRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<FoodResponse> getFoodsByCategory(Long categoryId) {

        return foodRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public List<FoodResponse> searchFood(String name) {

        return foodRepository.findByFoodNameContainingIgnoreCase(name)
                .stream()
                .map(this::map)
                .toList();
    }

    private FoodResponse map(Food food) {

        return FoodResponse.builder()
                .id(food.getId())
                .foodName(food.getFoodName())
                .description(food.getDescription())
                .price(food.getPrice())
                .imageUrl(food.getImageUrl())
                .veg(food.getVeg())
                .available(food.getAvailable())
                .rating(food.getRating())
                .restaurantId(food.getRestaurant().getId())
                .restaurantName(food.getRestaurant().getRestaurantName())
                .categoryId(food.getCategory().getId())
                .categoryName(food.getCategory().getCategoryName())
                .build();
    }
}