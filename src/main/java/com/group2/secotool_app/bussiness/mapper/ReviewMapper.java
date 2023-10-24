package com.group2.secotool_app.bussiness.mapper;

import com.group2.secotool_app.model.dto.request.ReviewRequestDto;
import com.group2.secotool_app.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "comment", target = "comment")
    @Mapping(source = "score", target = "score")
    Review toReview(ReviewRequestDto reviewRequestDto);
}
