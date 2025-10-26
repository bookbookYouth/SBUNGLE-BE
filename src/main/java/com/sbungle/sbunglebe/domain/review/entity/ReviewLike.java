package com.sbungle.sbunglebe.domain.review.entity;

import com.sbungle.sbunglebe.domain.review.entity.enums.LikeType;
import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_likes")
public class ReviewLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "like_id", unique = true)
    private String likeId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "review_id", nullable = false)
    private String reviewId;

    @Enumerated(EnumType.STRING)
    private LikeType likeType;

    public static ReviewLike createReviewLike(String userId, String reviewId, LikeType likeType) {
        return ReviewLike.builder()
                .userId(userId)
                .reviewId(reviewId)
                .likeType(likeType)
                .build();
    }
    @Builder(access = AccessLevel.PRIVATE)
    private ReviewLike(String userId, String reviewId, LikeType likeType) {
        this.likeId = java.util.UUID.randomUUID().toString();
        this.userId = userId;
        this.reviewId = reviewId;
        this.likeType = likeType;
    }


}
