package com.sbungle.sbunglebe.domain.review.controller;

import com.sbungle.sbunglebe.domain.review.entity.enums.LikeType;
import com.sbungle.sbunglebe.domain.review.service.ReviewLikeService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Slf4j
public class ReviewLikeController {

    private final ReviewLikeService reviewLikeService;

    @Operation(summary = "리뷰 공감 생성(LIKE, DISLIKE)")
    @PostMapping("/{reviewId}/likes")
    public void reviewLikeAdd(
            @PathVariable String reviewId,
            @RequestParam LikeType likeType,
            @AuthenticationPrincipal User currentUser

    ) {
        String userId = currentUser.getUsername();
        log.debug("리뷰 공감 생성 userID: {}, reviewID; {}", userId, reviewId);
        reviewLikeService.addReviewLike(reviewId, likeType, userId);
    }

    @Operation(summary = "리뷰 공감 삭제")
    @DeleteMapping("/{reviewId}/likes")
    public void reviewLikeRemove(
            @PathVariable String reviewId,
            @AuthenticationPrincipal User currentUser
    ) {
        String userId = currentUser.getUsername();
        log.debug("리뷰 공감 삭제 userID: {}, reviewID; {}", userId, reviewId);
        reviewLikeService.removeReviewLike(reviewId, userId);
    }

}
