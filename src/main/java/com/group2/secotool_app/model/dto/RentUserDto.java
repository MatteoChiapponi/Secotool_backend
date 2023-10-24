package com.group2.secotool_app.model.dto;

import java.util.List;

public record RentUserDto(
        Long productId,
        String productName,
        List<ImageDto> productImage,
        RentDto rentalData
) {
    public int compareTo(RentUserDto rentUserDto) {
        return this.rentalData.rentalStartDate().compareTo(rentUserDto.rentalData().rentalStartDate());
    }
}
