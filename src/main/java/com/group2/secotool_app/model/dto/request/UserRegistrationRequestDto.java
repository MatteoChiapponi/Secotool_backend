package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record UserRegistrationRequestDto(
        @NotBlank(message = "required firstName")
        String firstName,
        @NotBlank(message = "required lastName")
        String lastName,
        @Email(message = "invalid format email, must be like test@test.com")
        @NotBlank(message = "required username")
        String username,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,}$",
                message = "Password must have at least 6 characters and one uppercase and one number.")
        @NotBlank(message = "required password")
        String password
) {
}
