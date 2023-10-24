package com.group2.secotool_app.model.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record IdListRequestDto(
        @NotNull
        List<Long> idsList
) {
}
