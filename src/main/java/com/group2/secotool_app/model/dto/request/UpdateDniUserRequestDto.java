package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateDniUserRequestDto(
        @NotBlank
        String dni
) {
}
