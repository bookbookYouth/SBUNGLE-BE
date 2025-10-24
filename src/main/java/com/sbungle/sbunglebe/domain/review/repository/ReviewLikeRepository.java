package com.sbungle.sbunglebe.domain.review.repository;

import com.sbungle.sbunglebe.domain.review.entity.ReviewLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewLikeRepository extends JpaRepository<ReviewLikeEntity, Long> {
    boolean existsByUserIdAndReviewId(String userId, String reviewId);
    Optional<ReviewLikeEntity> findByReviewIdAndUserId(String reviewId, String userId);
}
