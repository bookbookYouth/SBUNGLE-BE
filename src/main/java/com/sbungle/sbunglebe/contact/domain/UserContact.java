package com.sbungle.sbunglebe.contact.domain;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import com.sbungle.sbunglebe.user.domain.UserEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user_contact")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class UserContact extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false, length = 2000)
    private String content;


    public static UserContact of(String userId, String content) {
        return UserContact.builder()
                .userId(userId)
                .content(content)
                .build();
    }

    @Builder(access = PRIVATE)
    private UserContact(String userId, String content) {
        this.userId = userId;
        this.content = content;
    }
}
