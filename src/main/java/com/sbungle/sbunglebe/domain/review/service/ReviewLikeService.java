package com.sbungle.sbunglebe.domain.review.service;

import com.sbungle.sbunglebe.domain.review.entity.ReviewLikeEntity;
import com.sbungle.sbunglebe.domain.review.entity.enums.LikeType;
import com.sbungle.sbunglebe.domain.review.reader.ReviewReader;
import com.sbungle.sbunglebe.domain.review.repository.ReviewLikeRepository;
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

    @Transactional
    public void addReviewLike(String reviewId, LikeType likeType, String userId) {
        ReviewLikeEntity.createReviewLike(reviewId, userId, likeType);
        reviewService.updateCount(reviewId, likeType, 1);
    }

    @Transactional
    public void removeReviewLike(String reviewId, String userId) {
        ReviewLikeEntity reviewLike = reviewReader.getReviewLikeByReviewIdAndUserId(reviewId, userId);
        reviewLikeRepository.delete(reviewLike);
        reviewService.updateCount(reviewId, reviewLike.getLikeType(), -1);
    }
}
