package com.group2.secotool_app.model.dto;

import com.group2.secotool_app.model.entity.UserRole;

public record UserDto(
        Long id,
        String firstName,
        String lastName,
        UserRole userRole
) {
}
