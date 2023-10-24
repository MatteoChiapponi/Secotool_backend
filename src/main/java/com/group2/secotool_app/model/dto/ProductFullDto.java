package com.group2.secotool_app.model.dto;

import java.util.List;

public record ProductFullDto(
        Long id,
        String name,
        String description,
        Double price,
        Double averageScore,
        Integer numberOfScores,
        Boolean isFavorite,
        List<ImageDto> images,
        List<FeatureDto> productFeatures,
        List<CategoryDto> productCategories,
        List<RentDto> productRentals,
        List<ReviewDto> productReviews
) {
}
