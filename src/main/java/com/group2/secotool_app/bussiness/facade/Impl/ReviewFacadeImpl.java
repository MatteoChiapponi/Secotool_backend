package com.group2.secotool_app.bussiness.facade.Impl;

import com.group2.secotool_app.bussiness.facade.IReviewFacade;
import com.group2.secotool_app.bussiness.mapper.ReviewMapper;
import com.group2.secotool_app.bussiness.service.IProductService;
import com.group2.secotool_app.bussiness.service.IReviewService;
import com.group2.secotool_app.model.dto.request.ReviewRequestDto;
import com.group2.secotool_app.model.entity.Product;
import com.group2.secotool_app.model.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewFacadeImpl implements IReviewFacade {

    private final IReviewService reviewService;
    private final ReviewMapper reviewMapper;
    private final IProductService productService;

    @Override
    public void saveReview(ReviewRequestDto reviewRequestDto, Long userId, Long productId) {
        var review = reviewMapper.toReview(reviewRequestDto);
        review.setUser(new User(userId));
        review.setProduct(new Product(productId));
        reviewService.save(review);
        productService.updateAverageAndNumberOfScore(reviewRequestDto.score(), productId);
    }


}
