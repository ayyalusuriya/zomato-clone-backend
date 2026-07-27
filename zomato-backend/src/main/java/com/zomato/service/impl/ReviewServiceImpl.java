package com.zomato.service.impl;

import com.zomato.dto.ReviewRequest;
import com.zomato.dto.ReviewResponse;
import com.zomato.entity.Restaurant;
import com.zomato.entity.Review;
import com.zomato.entity.User;
import com.zomato.repository.RestaurantRepository;
import com.zomato.repository.ReviewRepository;
import com.zomato.repository.UserRepository;
import com.zomato.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Override
    public ReviewResponse addReview(ReviewRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Restaurant restaurant = restaurantRepository.findById(request.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        reviewRepository.findByUserAndRestaurant(user, restaurant)
                .ifPresent(r -> {
                    throw new RuntimeException("You have already reviewed this restaurant");
                });

        Review review = Review.builder()
                .rating(request.getRating())
                .review(request.getReview())
                .user(user)
                .restaurant(restaurant)
                .build();

        Review saved = reviewRepository.save(review);

        updateRestaurantRating(restaurant);

        return map(saved);
    }

    @Override
    public List<ReviewResponse> getRestaurantReviews(Long restaurantId) {

        return reviewRepository.findByRestaurantId(restaurantId)
                .stream()
                .map(this::map)
                .toList();
    }

    @Override
    public void deleteReview(Long reviewId) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        Restaurant restaurant = review.getRestaurant();

        reviewRepository.delete(review);

        updateRestaurantRating(restaurant);
    }

    private void updateRestaurantRating(Restaurant restaurant) {

        List<Review> reviews = reviewRepository.findByRestaurantId(restaurant.getId());

        if (reviews.isEmpty()) {
            restaurant.setAverageRating(0.0);
            restaurant.setTotalReviews(0);
        } else {

            double avg = reviews.stream()
                    .mapToInt(Review::getRating)
                    .average()
                    .orElse(0.0);

            restaurant.setAverageRating(avg);
            restaurant.setTotalReviews(reviews.size());
        }

        restaurantRepository.save(restaurant);
    }

    private ReviewResponse map(Review review) {

        return ReviewResponse.builder()
                .id(review.getId())
                .userName(review.getUser().getFullName())
                .restaurantName(review.getRestaurant().getRestaurantName())
                .rating(review.getRating())
                .review(review.getReview())
                .reviewDate(review.getReviewDate())
                .build();
    }
}