package com.group2.secotool_app.presentation.Controllers;

import com.group2.secotool_app.bussiness.facade.IReviewFacade;
import com.group2.secotool_app.model.dto.request.RentProductRequestDto;
import com.group2.secotool_app.model.dto.request.ReviewRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final IReviewFacade reviewFacade;

    @PostMapping("/{productId}")
    public ResponseEntity<String> saveReview(@RequestBody @Valid ReviewRequestDto reviewRequestDto, @PathVariable Long productId){
        Long userId = Long.parseLong((String) SecurityContextHolder.getContext().getAuthentication().getCredentials());
        reviewFacade.saveReview(reviewRequestDto, userId, productId);
        return ResponseEntity.ok(String.format("user %s's review of product %s was saved successfully",userId,productId));
    }

}
