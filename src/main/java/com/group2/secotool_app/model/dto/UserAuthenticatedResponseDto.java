package com.group2.secotool_app.model.dto;

public record UserAuthenticatedResponseDto(
        String jwt,
        UserDto userInfo
){
}
