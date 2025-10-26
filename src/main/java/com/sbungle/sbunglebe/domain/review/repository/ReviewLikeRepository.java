package com.sbungle.sbunglebe.domain.review.repository;

import com.sbungle.sbunglebe.domain.review.entity.ReviewLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLike, Long> {
    Boolean existsByUserIdAndReviewId(String userId, String reviewId);
    Optional<ReviewLike> findByReviewIdAndUserId(String reviewId, String userId);
}
