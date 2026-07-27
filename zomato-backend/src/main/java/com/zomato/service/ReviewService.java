package com.zomato.service;

import com.zomato.dto.ReviewRequest;
import com.zomato.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {

    ReviewResponse addReview(ReviewRequest request);

    List<ReviewResponse> getRestaurantReviews(Long restaurantId);

    void deleteReview(Long reviewId);

}