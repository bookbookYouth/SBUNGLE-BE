package com.sbungle.sbunglebe.inquiry.domain;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "inquiry")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class Inquiry extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false, length = 2000)
    private String content;


    public static Inquiry of(String userId, String content) {
        return Inquiry.builder()
                .userId(userId)
                .content(content)
                .build();
    }

    @Builder(access = PRIVATE)
    private Inquiry(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
