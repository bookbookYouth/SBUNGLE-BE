package com.sbungle.sbunglebe.domain.bookScrap.entity;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book_scraps")
public class BookScrap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "book_scrap_id", unique = true)
    private String bookScrapId;

    @Column(name = "book_id", nullable = false)
    private String bookId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    public static BookScrap createBookScrap(String bookId, String userId) {
        return BookScrap.builder()
                .bookId(bookId)
                .userId(userId)
                .build();
    }

    @Builder(access = AccessLevel.PRIVATE)
    private BookScrap(String bookId, String userId) {
        this.bookScrapId = UUID.randomUUID().toString();
        this.bookId = bookId;
        this.userId = userId;
    }
}
