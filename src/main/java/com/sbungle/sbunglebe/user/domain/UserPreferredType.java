package com.sbungle.sbunglebe.user.domain;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntity;
import com.sbungle.sbunglebe.user.domain.enums.PreferredType;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "user_preferred_type")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class UserPreferredType extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(referencedColumnName = "id", name = "user_id", nullable = false)
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PreferredType preferredType;

    public static UserPreferredType of(UserEntity user, PreferredType preferredType) {
        return UserPreferredType.builder()
                .user(user)
                .preferredType(preferredType)
                .build();
    }

    @Builder(access = PRIVATE)
    private UserPreferredType(UserEntity user, PreferredType preferredType) {
        this.user = user;
        this.preferredType = preferredType;
    }

    public void setUser(UserEntity user) {
        this.user = user;
        if (!user.getPreferredTypes().contains(this)) {
            user.getPreferredTypes().add(this);
        }
    }

}
