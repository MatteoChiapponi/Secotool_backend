package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record PoliticRequestDto(
        @NotBlank(message = "required title")
        String title,
        @NotBlank(message = "required description")
        String description
) {
}
