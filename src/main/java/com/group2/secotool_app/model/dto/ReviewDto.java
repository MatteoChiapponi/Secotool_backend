package com.group2.secotool_app.model.dto;

import java.time.LocalDate;

public record ReviewDto(

        Long id,
        String comment,
        Double score,
        LocalDate reviewDay,
        UserDto user

) {
}
