package com.sbungle.sbunglebe.domain.review.controller;

import com.sbungle.sbunglebe.domain.review.entity.enums.LikeType;
import com.sbungle.sbunglebe.domain.review.service.ReviewLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;

    @PostMapping("/{reviewId}")
    public void reviewLikeAdd(
            @PathVariable String reviewId,
            @RequestBody LikeType likeType,
            String userId

    ) {
        reviewLikeService.addReviewLike(reviewId, likeType, userId);
    }

    @DeleteMapping("/{reviewId}")
    public void reviewLikeRemove(
            @PathVariable String reviewId,
            String userId
    ) {
        reviewLikeService.removeReviewLike(reviewId, userId);
    }

}
