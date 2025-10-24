package com.sbungle.sbunglebe.domain.review.service;

import com.sbungle.sbunglebe.domain.review.entity.ReviewLike;
import com.sbungle.sbunglebe.domain.review.entity.enums.LikeType;
import com.sbungle.sbunglebe.domain.review.reader.ReviewReader;
import com.sbungle.sbunglebe.domain.review.repository.ReviewLikeRepository;
import com.sbungle.sbunglebe.domain.review.validator.ReviewLikeValidator;
import com.sbungle.sbunglebe.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewLikeService {
    private final ReviewService reviewService;
    private final ReviewReader reviewReader;
    private final ReviewLikeRepository reviewLikeRepository;
    private final UserService userService;
    private final ReviewLikeValidator reviewLikeValidator;

    @Transactional
    public void addReviewLike(String reviewId, LikeType likeType, String userId) {
        userService.findUserByIdOrThrow(userId); // user검증
        reviewLikeValidator.validateReviewLikePost(userId, reviewId);
        ReviewLike reviewLike = ReviewLike.createReviewLike(userId, reviewId, likeType);
        reviewService.updateCount(reviewId, likeType, 1);
        reviewLikeRepository.save(reviewLike);
    }

    @Transactional
    public void removeReviewLike(String reviewId, String userId) {
        userService.findUserByIdOrThrow(userId); // user검증
        reviewLikeValidator.validateReviewLikeDelete(userId, reviewId);
        ReviewLike reviewLike = reviewReader.getReviewLikeByReviewIdAndUserId(reviewId, userId);
        reviewLikeRepository.delete(reviewLike);
        reviewService.updateCount(reviewId, reviewLike.getLikeType(), -1);
    }
}
