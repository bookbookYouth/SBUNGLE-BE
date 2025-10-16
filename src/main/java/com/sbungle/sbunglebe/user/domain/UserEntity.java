package com.sbungle.sbunglebe.user.domain;

import com.sbungle.sbunglebe.user.domain.enums.Role;
import com.sbungle.sbunglebe.user.domain.enums.SocialLoginType;
import com.sbungle.sbunglebe.user.dto.response.KakaoResourceServerResponse;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

import static jakarta.persistence.EnumType.STRING;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "`user`")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class UserEntity {

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

    @Column(nullable = true, unique = true)
    private String nickName;

    private boolean enabled;

    @Enumerated(STRING)
    private Role role;

    @Enumerated(STRING)
    SocialLoginType socialLoginType = SocialLoginType.NONE;



    public static UserEntity fromKakaoResponse(KakaoResourceServerResponse serverResponse) {
        return UserEntity.builder()
                .email(serverResponse.kakaoAccount().email())
                .name(serverResponse.properties().nickname())
                .imageUrl(serverResponse.properties().profileImage())
                .nickName(serverResponse.properties().nickname())

                .socialLoginType(SocialLoginType.KAKAO)
                .role(Role.USER)
                .enabled(false)
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


}
