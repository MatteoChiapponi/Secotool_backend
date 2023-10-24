package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RentProductRequestDto(
        @NotNull(message = "required productId")
        Long productId,
        @NotNull(message = "required startDate")
        LocalDate startDate,
        @NotNull(message = "required endDate")
        LocalDate endDate
) {
}
