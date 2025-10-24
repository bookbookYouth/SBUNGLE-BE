package com.sbungle.sbunglebe.domain.review.reader;

import com.sbungle.sbunglebe.domain.review.entity.Review;
import com.sbungle.sbunglebe.domain.review.entity.ReviewLike;
import com.sbungle.sbunglebe.domain.review.exception.ReviewErrorCode;
import com.sbungle.sbunglebe.domain.review.exception.ReviewException;
import com.sbungle.sbunglebe.domain.review.repository.ReviewLikeRepository;
import com.sbungle.sbunglebe.domain.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewReader {
    private final ReviewRepository reviewRepository;
    private final ReviewLikeRepository reviewLikeRepository;

    public Review getReviewByReviewId(String reviewId) {
        return reviewRepository.findByReviewId(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.NOT_FOUND_REVIEW, reviewId));
    }

    public ReviewLike getReviewLikeByReviewIdAndUserId(String reviewId, String userId) {
        return reviewLikeRepository.findByReviewIdAndUserId(reviewId, userId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.NOT_FOUND_REVIEW_LIKE, reviewId));
    }
}
