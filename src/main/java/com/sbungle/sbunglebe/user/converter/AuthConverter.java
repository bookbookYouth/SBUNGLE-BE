package com.sbungle.sbunglebe.user.converter;

import com.sbungle.sbunglebe.user.domain.UserEntity;
import com.sbungle.sbunglebe.user.dto.response.SocialLoginResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;
import static org.mapstruct.ReportingPolicy.IGNORE;

@Mapper(
        componentModel = SPRING,
        unmappedTargetPolicy = IGNORE
)
public interface AuthConverter {

    AuthConverter INSTANCE = Mappers.getMapper(AuthConverter.class);

    SocialLoginResponse toSocialLoginResponse(UserEntity userEntity);

    default String map(Long value) {
        return value != null ? String.valueOf(value) : null;
    }


    default MultiValueMap<String, Object> toKakaoTokenRequest(String accessCode, String kakaoClientId, String kakaoRedirectUri) {
        MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", kakaoClientId);
        formData.add("redirect_uri", kakaoRedirectUri);
        formData.add("code", accessCode);
        formData.add("client_secret", "");
        return formData;
    }

}
