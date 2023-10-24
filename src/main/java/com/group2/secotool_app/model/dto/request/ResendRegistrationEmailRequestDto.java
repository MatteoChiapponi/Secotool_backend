package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record ResendRegistrationEmailRequestDto (
        @NotBlank(message = "required firstName")
        String firstName,
        @NotBlank(message = "required lastName")
        String lastName,
        @Email(message = "invalid format email, must be like test@test.com")
        @NotBlank(message = "required username")
        String username
) {
}
