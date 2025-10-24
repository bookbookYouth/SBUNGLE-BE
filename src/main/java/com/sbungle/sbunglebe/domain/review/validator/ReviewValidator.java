package com.sbungle.sbunglebe.domain.review.validator;

import com.sbungle.sbunglebe.domain.review.exception.ReviewErrorCode;
import com.sbungle.sbunglebe.domain.review.repository.ReviewLikeRepository;
import com.sbungle.sbunglebe.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReviewValidator {
    private final ReviewLikeRepository reviewLikeRepository;

    public void notExistReviewLike(String userId, String reviewId) {
        if(!reviewLikeRepository.existsByUserIdAndReviewId(userId, reviewId)){
            return;
        } else {
            throw new CustomException(ReviewErrorCode.ALREADY_LIKE_OR_UNLIKE, reviewId) {
            };
        }

    }
}
