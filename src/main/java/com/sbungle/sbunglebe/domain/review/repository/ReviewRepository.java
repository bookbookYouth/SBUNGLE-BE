package com.sbungle.sbunglebe.domain.review.repository;

import com.sbungle.sbunglebe.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    Optional<Review> findByReviewId(String reviewId);
    List<Review> findByBookIdOrderByLikeCountDesc(String bookId);
    List<Review> findByBookIdOrderByCreatedAtDesc(String bookId);
}
