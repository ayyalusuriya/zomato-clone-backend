package com.zomato.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReviewResponse {

    private Long id;

    private String userName;

    private String restaurantName;

    private Integer rating;

    private String review;

    private LocalDateTime reviewDate;
}