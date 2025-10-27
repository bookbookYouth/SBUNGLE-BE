package com.sbungle.sbunglebe.domain.book.entity;

import com.sbungle.sbunglebe.domain.bookstore.entity.Bookstore;
import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
public class Book extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_id", unique = true)
    private String bookId;

    @Column(name = "book_title")
    private String title;  // 추천대상 ex. 내리는 금값 지금이 매수 시점일까?

    @Column(name = "genre")
    @Enumerated(EnumType.STRING)
    private PreferredGenre genre;

    @Column(name = "price")
    private int price;

    @Column(name = "sentence", columnDefinition = "TEXT")
    private String sentence;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "mood")
    @Enumerated(EnumType.STRING)
    private PreferredType mood;  // 분위기

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "author")
    private String author;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "review_score")
    private float reviewTotalScore;

    @Column(name = "present_count")
    private int presentCount;

    public void increaseLikeCount() { this.likeCount++; }

    public void decreaseLikeCount() {
        if(this.likeCount > 0) { this.likeCount--; }
    }

    public void increaseReviewCount() { this.reviewCount++; }

    public void decreaseReviewCount() {
        if(this.reviewCount > 0) { this.reviewCount--; }
    }

    public void updateReviewTotalScore(float newScore) { this.reviewTotalScore += newScore; }



}
