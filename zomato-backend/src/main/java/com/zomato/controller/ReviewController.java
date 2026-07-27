package com.zomato.controller;

import com.zomato.dto.ReviewRequest;
import com.zomato.dto.ReviewResponse;
import com.zomato.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ReviewResponse addReview(@Valid @RequestBody ReviewRequest request) {

        return reviewService.addReview(request);
    }

    @GetMapping("/restaurant/{restaurantId}")
    public List<ReviewResponse> getRestaurantReviews(@PathVariable Long restaurantId) {

        return reviewService.getRestaurantReviews(restaurantId);
    }

    @DeleteMapping("/{reviewId}")
    public String deleteReview(@PathVariable Long reviewId) {

        reviewService.deleteReview(reviewId);

        return "Review Deleted Successfully";
    }
}