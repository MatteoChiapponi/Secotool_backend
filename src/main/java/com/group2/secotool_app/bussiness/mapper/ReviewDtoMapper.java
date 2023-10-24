package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.ReviewDto;
import com.group2.secotool_app.model.entity.Review;
import org.mapstruct.Mapping;

public interface ReviewDtoMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "score", target = "score")
    @Mapping(source = "reviewDay", target = "reviewDay")
    @Mapping(source = "user", target = "user")

    ReviewDto toReviewDto (Review review);
}
