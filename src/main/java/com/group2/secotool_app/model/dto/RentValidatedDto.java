package com.group2.secotool_app.model.dto;

import java.time.LocalDate;

public record RentValidatedDto(
        LocalDate startDate,
        LocalDate endDate,
        long totalDays,
        Double TotalPrice
) {
}
