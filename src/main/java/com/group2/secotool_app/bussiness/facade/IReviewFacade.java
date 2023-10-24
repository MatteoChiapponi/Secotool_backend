package com.group2.secotool_app.bussiness.facade;

import com.group2.secotool_app.model.dto.request.ReviewRequestDto;

public interface IReviewFacade {
    void saveReview(ReviewRequestDto reviewRequestDto, Long userId, Long productId);

}
