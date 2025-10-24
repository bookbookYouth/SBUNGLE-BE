package com.sbungle.sbunglebe.domain.review.validator;

import com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapException;
import com.sbungle.sbunglebe.domain.review.exception.ReviewException;
import com.sbungle.sbunglebe.domain.review.repository.ReviewLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapErrorCode.ALREADY_EXIST_BOOKSCRAP;
import static com.sbungle.sbunglebe.domain.bookScrap.exception.BookScrapErrorCode.NOT_FOUND_BOOKSCRAP;
import static com.sbungle.sbunglebe.domain.review.exception.ReviewErrorCode.ALREADY_LIKE_OR_UNLIKE;
import static com.sbungle.sbunglebe.domain.review.exception.ReviewErrorCode.NOT_FOUND_REVIEW_LIKE;

@Component
@RequiredArgsConstructor
public class ReviewLikeValidator {
    private final ReviewLikeRepository reviewLikeRepository;

    public void validateReviewLikePost(String userId, String reviewId) {
        if(reviewLikeRepository.existsByUserIdAndReviewId(userId, reviewId)) {
            throw new ReviewException(ALREADY_LIKE_OR_UNLIKE);
        }
    }

    public void validateReviewLikeDelete(String userId, String reviewId) {
        if(!reviewLikeRepository.existsByUserIdAndReviewId(userId, reviewId)) {
            throw new ReviewException(NOT_FOUND_REVIEW_LIKE);
        }
    }
}
