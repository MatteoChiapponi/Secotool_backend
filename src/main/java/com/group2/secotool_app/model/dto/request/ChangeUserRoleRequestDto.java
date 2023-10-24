package com.group2.secotool_app.model.dto.request;

import com.group2.secotool_app.model.entity.UserRole;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record ChangeUserRoleRequestDto(
        @Min(1)
        Long userId,
        @Pattern(regexp = "^(USER|ADMIN)$", message = "Role can be only USER or ADMIN")
        String userRole
) {
}
