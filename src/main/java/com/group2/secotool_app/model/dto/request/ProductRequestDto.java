package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProductRequestDto(

        @NotBlank(message = "required name")
        String name,
        @NotBlank(message = "required description")
        String description,
        @NotNull(message = "required price")
        Double price
) {
}

