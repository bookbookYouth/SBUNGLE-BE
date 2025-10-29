package com.sbungle.sbunglebe.user.domain;

import com.sbungle.sbunglebe.global.domain.BaseTimeEntityWithDeletion;
import com.sbungle.sbunglebe.user.domain.enums.*;
import com.sbungle.sbunglebe.user.domain.enums.Role;
import com.sbungle.sbunglebe.user.domain.enums.SocialLoginType;
import com.sbungle.sbunglebe.user.dto.response.KakaoResourceServerResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "`user`")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class UserEntity extends BaseTimeEntityWithDeletion {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String userId;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    // 실명
    @Column(length = 30)
    private String name;

    @Column(length = 1024)
    private String imageUrl;

    @Column(nullable = true)
    private String nickName;

    private boolean enabled;

    @Enumerated(STRING)
    private Role role;

    @Enumerated(STRING)
    SocialLoginType socialLoginType = SocialLoginType.NONE;

    @Enumerated(STRING)
    private Gender gender;
    private Integer age;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPreferredGenre> preferredGenres = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserPreferredType> preferredTypes = new ArrayList<>();

    public static UserEntity fromKakaoResponse(KakaoResourceServerResponse serverResponse) {
        return UserEntity.builder()
                .email(serverResponse.kakaoAccount().email())
                .name(serverResponse.properties().nickname())
                .imageUrl(serverResponse.properties().profileImage())
                .nickName(serverResponse.properties().nickname())

                .socialLoginType(SocialLoginType.KAKAO)
                .role(Role.USER)
                .enabled(true)
                .build();
    }

    @Builder(access = PRIVATE)
    private UserEntity(String email, String name, String nickName, boolean enabled, Role role, String imageUrl, SocialLoginType socialLoginType) {

        this.userId = UUID.randomUUID().toString();

        this.email = email;
        this.name = name;
        this.nickName = nickName;
        this.enabled = enabled;
        this.role = role;
        this.imageUrl = imageUrl;
        this.socialLoginType = socialLoginType;

    }

    public void updateGender(Gender gender) {
        this.gender = gender;
    }

    public void updateAge(Integer age) {
        this.age = age;
    }

    public void addPreferredGenre(UserPreferredGenre userPreferredGenre) {
        this.preferredGenres.add(userPreferredGenre);
        if (userPreferredGenre.getUser() != this) {
            userPreferredGenre.setUser(this);
        }
    }

    public void addPreferredType(UserPreferredType userPreferredType) {
        this.preferredTypes.add(userPreferredType);
        if (userPreferredType.getUser() != this) {
            userPreferredType.setUser(this);
        }
    }

}
