package com.sbungle.sbunglebe.domain.book.entity;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
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

    @Column(name = "recommendation")
    private String recommendation;  // 추천대상 ex. 내리는 금값 지금이 매수 시점일까?

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "price")
    private int price;

    @Column(name = "sentence", columnDefinition = "TEXT")
    private String sentence;

    @Column(name = "store_id")
    private String storeId;

    @Column(name = "mood")
    @Enumerated(EnumType.STRING)
    private Mood mood;  // 분위기

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "author")
    private String author;

    @Column(name = "like_count")
    private int likeCount;

    @Column(name = "review_count")
    private int reviewCount;

    @Column(name = "present_count")
    private int presentCount;

}
