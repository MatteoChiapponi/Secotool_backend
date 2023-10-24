package com.group2.secotool_app.model.dto;

import java.time.LocalDate;

public record RentDto(
        Long id,
        LocalDate rentalDay,
        LocalDate rentalStartDate,
        LocalDate rentalEndDate,
        Long totalDays,
        Double rentalPrice
) {

    public int compareTo(RentDto rentDto) {
        return this.rentalStartDate.compareTo(rentDto.rentalStartDate);
    }
}
