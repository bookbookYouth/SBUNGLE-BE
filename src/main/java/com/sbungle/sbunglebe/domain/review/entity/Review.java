package com.sbungle.sbunglebe.domain.review.entity;

import com.sbungle.sbunglebe.domain.review.dto.request.ReviewCreateRequest;
import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "reviews")
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "review_id", unique = true)
    private String reviewId;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "content", nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(name = "score", nullable = false)
    private float score;

    @Column(name = "is_spoiler", nullable = false)
    private boolean isSpoiler;

    @Column(name = "like_count", nullable = false)
    private int likeCount;

    @Column(name = "dislike_count", nullable = false)
    private int dislikeCount;

    public void increaseLikeCount() {
        this.likeCount++;
    }

    public void decreaseLikeCount() {
        if (this.likeCount > 0) {
            this.likeCount--;
        }
    }

    public void increaseDislikeCount() {
        this.dislikeCount++;
    }

    public void decreaseDislikeCount() {
        if (this.dislikeCount > 0) {
            this.dislikeCount--;
        }
    }

    public static Review createReview(String bookId, String userId, ReviewCreateRequest reviewCreateRequest){
        return Review.builder()
                .bookId(bookId)
                .userId(userId)
                .content(reviewCreateRequest.content())
                .score(reviewCreateRequest.score())
                .isSpoiler(reviewCreateRequest.isSpoiler())
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Review(String bookId, String userId, String content, float score, boolean isSpoiler) {
        this.reviewId = UUID.randomUUID().toString();
        this.bookId = bookId;
        this.userId = userId;
        this.content = content;
        this.score = score;
        this.isSpoiler = isSpoiler;
        this.likeCount = 0;
        this.dislikeCount = 0;
    }
}
