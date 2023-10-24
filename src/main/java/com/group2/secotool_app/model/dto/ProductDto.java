package com.group2.secotool_app.model.dto;

import java.util.List;
public record ProductDto(
        Long id,
        String name,
        String description,
        Double price,
        Double averageScore,
        Boolean isFavorite,
        List<ImageDto> images,
        List<CategoryDto> productCategories

){

    public ProductDto {
        if (isFavorite == null)
            isFavorite = false;

        if (averageScore == null || averageScore == 0)
            averageScore = 0.0;
    }
    public int compareTo(ProductDto productDto) {
        return Long.compare(this.id, productDto.id);
    }

}
