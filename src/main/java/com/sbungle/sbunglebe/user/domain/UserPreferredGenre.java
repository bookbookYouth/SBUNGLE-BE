package com.sbungle.sbunglebe.user.domain;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import com.sbungle.sbunglebe.global.domain.BaseTimeEntityWithDeletion;
import com.sbungle.sbunglebe.user.domain.enums.PreferredGenre;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user_preferred_genre")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class UserPreferredGenre extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferredGenre genre;

    public static UserPreferredGenre of(UserEntity user, PreferredGenre genre) {
        return UserPreferredGenre.builder()
                .user(user)
                .genre(genre)
                .build();
    }

    @Builder(access = PRIVATE)
    private UserPreferredGenre(UserEntity user, PreferredGenre genre) {
        this.user = user;
        this.genre = genre;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        if (!user.getPreferredGenres().contains(this)) {
            user.getPreferredGenres().add(this);
        }
    }

}
