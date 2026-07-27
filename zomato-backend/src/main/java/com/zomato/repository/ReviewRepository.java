package com.zomato.repository;

import com.zomato.entity.Restaurant;
import com.zomato.entity.Review;
import com.zomato.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByRestaurantId(Long restaurantId);

    Optional<Review> findByUserAndRestaurant(User user, Restaurant restaurant);

}