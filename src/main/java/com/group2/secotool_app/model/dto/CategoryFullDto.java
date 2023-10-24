package com.group2.secotool_app.model.dto;

public record CategoryFullDto (
        Long id,
        String name,
        String description,
        ImageDto image
){
}
