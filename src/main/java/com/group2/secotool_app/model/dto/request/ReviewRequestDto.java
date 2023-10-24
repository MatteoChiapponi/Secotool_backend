package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewRequestDto(
        @NotBlank(message = "required comment")
        String comment,
        @NotNull(message = "required score")
        @Min(1)
        @Max(5)
        Double score
) {
}
