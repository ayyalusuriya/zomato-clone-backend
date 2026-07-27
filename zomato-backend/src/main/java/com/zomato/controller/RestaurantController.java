package com.zomato.controller;

import com.zomato.dto.RestaurantRequest;
import com.zomato.dto.RestaurantResponse;
import com.zomato.service.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // ADMIN & RESTAURANT_OWNER
    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PostMapping
    public ResponseEntity<RestaurantResponse> addRestaurant(
            @Valid @RequestBody RestaurantRequest request) {

        RestaurantResponse response = restaurantService.addRestaurant(request);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    // Everyone logged in
    @GetMapping
    public ResponseEntity<List<RestaurantResponse>> getAllRestaurants() {

        return ResponseEntity.ok(
                restaurantService.getAllRestaurants());
    }

    // Everyone logged in
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantResponse> getRestaurantById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                restaurantService.getRestaurantById(id));
    }

    // ADMIN & RESTAURANT_OWNER
    @PreAuthorize("hasAnyRole('ADMIN','RESTAURANT_OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantResponse> updateRestaurant(
            @PathVariable Long id,
            @Valid @RequestBody RestaurantRequest request) {

        return ResponseEntity.ok(
                restaurantService.updateRestaurant(id, request));
    }

    // ADMIN only
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant(
            @PathVariable Long id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.ok("Restaurant Deleted Successfully");
    }

    // Everyone logged in
    @GetMapping("/search")
    public ResponseEntity<List<RestaurantResponse>> searchRestaurant(
            @RequestParam String name) {

        return ResponseEntity.ok(
                restaurantService.searchRestaurant(name));
    }
}