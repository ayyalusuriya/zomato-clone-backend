package com.zomato.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {

    private Long id;
    private String categoryName;
    private String description;
    private Boolean active;

}