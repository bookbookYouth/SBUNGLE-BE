package com.sbungle.sbunglebe.domain.review.dto.response;

import com.sbungle.sbunglebe.domain.bookScrap.entity.BookScrap;
import com.sbungle.sbunglebe.domain.review.entity.Review;
import com.sbungle.sbunglebe.global.util.formatter.DateFormatter;

public record ReviewResponse(
        String reviewId,
        String username,
        boolean isSpoiler,
        double score,
        String content,
        int likeCount,
        int dislikeCount,
        DateFormatter createdAt
) {
    public static ReviewResponse of(Review review, String username) {
        return new ReviewResponse(
                review.getReviewId(),
                username,
                review.isSpoiler(),
                review.getScore(),
                review.getContent(),
                review.getLikeCount(),
                review.getDislikeCount(),
                new DateFormatter(review.getCreatedAt())
        );
    }
}
