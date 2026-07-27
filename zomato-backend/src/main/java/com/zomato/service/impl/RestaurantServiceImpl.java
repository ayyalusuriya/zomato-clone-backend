package com.zomato.service.impl;

import com.zomato.dto.RestaurantRequest;
import com.zomato.dto.RestaurantResponse;
import com.zomato.entity.Restaurant;
import com.zomato.entity.User;
import com.zomato.enums.Role;
import com.zomato.repository.RestaurantRepository;
import com.zomato.repository.UserRepository;
import com.zomato.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    @Override
    public RestaurantResponse addRestaurant(RestaurantRequest request) {

        User owner = userRepository.findById(request.getOwnerId())
                .orElseThrow(() -> new RuntimeException("Restaurant Owner not found"));

        if (owner.getRole() != Role.RESTAURANT_OWNER) {
            throw new RuntimeException("Selected user is not a Restaurant Owner");
        }

        Restaurant restaurant = Restaurant.builder()
                .restaurantName(request.getRestaurantName())
                .description(request.getDescription())
                .address(request.getAddress())
                .city(request.getCity())
                .state(request.getState())
                .pincode(request.getPincode())
                .phone(request.getPhone())
                .email(request.getEmail())
                .openingTime(request.getOpeningTime())
                .closingTime(request.getClosingTime())
                .imageUrl(request.getImageUrl())
                .rating(0.0)
                .active(true)
                .averageRating(0.0)
                .totalReviews(0)
                .owner(owner)
                .build();

        restaurant = restaurantRepository.save(restaurant);

        return mapToResponse(restaurant);
    }

    @Override
    public List<RestaurantResponse> getAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public RestaurantResponse getRestaurantById(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return mapToResponse(restaurant);
    }

    @Override
    public RestaurantResponse updateRestaurant(Long id, RestaurantRequest request) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setRestaurantName(request.getRestaurantName());
        restaurant.setDescription(request.getDescription());
        restaurant.setAddress(request.getAddress());
        restaurant.setCity(request.getCity());
        restaurant.setState(request.getState());
        restaurant.setPincode(request.getPincode());
        restaurant.setPhone(request.getPhone());
        restaurant.setEmail(request.getEmail());
        restaurant.setOpeningTime(request.getOpeningTime());
        restaurant.setClosingTime(request.getClosingTime());
        restaurant.setImageUrl(request.getImageUrl());

        if (request.getOwnerId() != null) {

            User owner = userRepository.findById(request.getOwnerId())
                    .orElseThrow(() -> new RuntimeException("Restaurant Owner not found"));

            if (owner.getRole() != Role.RESTAURANT_OWNER) {
                throw new RuntimeException("Selected user is not a Restaurant Owner");
            }

            restaurant.setOwner(owner);
        }

        restaurant = restaurantRepository.save(restaurant);

        return mapToResponse(restaurant);
    }

    @Override
    public void deleteRestaurant(Long id) {

        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurantRepository.delete(restaurant);
    }

    @Override
    public List<RestaurantResponse> searchRestaurant(String name) {

        return restaurantRepository
                .findByRestaurantNameContainingIgnoreCase(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private RestaurantResponse mapToResponse(Restaurant restaurant) {

        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .restaurantName(restaurant.getRestaurantName())
                .description(restaurant.getDescription())
                .address(restaurant.getAddress())
                .city(restaurant.getCity())
                .state(restaurant.getState())
                .pincode(restaurant.getPincode())
                .phone(restaurant.getPhone())
                .email(restaurant.getEmail())
                .openingTime(restaurant.getOpeningTime())
                .closingTime(restaurant.getClosingTime())
                .imageUrl(restaurant.getImageUrl())
                .rating(restaurant.getRating())
                .active(restaurant.getActive())
                .ownerId(
                        restaurant.getOwner() != null
                                ? restaurant.getOwner().getId()
                                : null
                )
                .ownerName(
                        restaurant.getOwner() != null
                                ? restaurant.getOwner().getFullName()
                                : null
                )
                .build();
    }
}