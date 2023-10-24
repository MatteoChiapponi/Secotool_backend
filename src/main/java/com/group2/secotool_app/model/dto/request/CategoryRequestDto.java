package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequestDto(
        @NotBlank(message = "required name")
        String name,
        @NotBlank(message = "required description")
        String description
) {
}
