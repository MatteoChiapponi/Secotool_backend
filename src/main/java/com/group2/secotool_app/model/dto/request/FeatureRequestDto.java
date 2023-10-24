package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record FeatureRequestDto(
        @NotBlank(message = "name required")
        String name,
        @NotBlank(message = "icon required")
        String icon
) {
}
