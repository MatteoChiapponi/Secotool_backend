package com.group2.secotool_app.model.dto;

public record ImageDto(
        Long id,
        String url

) {
    public ImageDto(Long id) {
        this(id, null);
    }
}
