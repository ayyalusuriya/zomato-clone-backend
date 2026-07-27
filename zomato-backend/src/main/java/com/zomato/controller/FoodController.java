package com.zomato.controller;

import com.zomato.dto.FoodRequest;
import com.zomato.dto.FoodResponse;
import com.zomato.service.FoodService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@RequiredArgsConstructor
public class FoodController {

    private final FoodService foodService;

    @PostMapping
    public FoodResponse addFood(@RequestBody FoodRequest request) {
        return foodService.addFood(request);
    }

    @GetMapping
    public List<FoodResponse> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/{id}")
    public FoodResponse getFoodById(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @PutMapping("/{id}")
    public FoodResponse updateFood(@PathVariable Long id,
                                   @RequestBody FoodRequest request) {
        return foodService.updateFood(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteFood(@PathVariable Long id) {
        foodService.deleteFood(id);
        return "Food Deleted Successfully";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<FoodResponse> getFoodsByRestaurant(@PathVariable Long restaurantId) {
        return foodService.getFoodsByRestaurant(restaurantId);
    }

    @GetMapping("/category/{categoryId}")
    public List<FoodResponse> getFoodsByCategory(@PathVariable Long categoryId) {
        return foodService.getFoodsByCategory(categoryId);
    }

    @GetMapping("/search")
    public List<FoodResponse> searchFood(@RequestParam String name) {
        return foodService.searchFood(name);
    }
}