package com.sbungle.sbunglebe.user.client;

import com.sbungle.sbunglebe.user.dto.response.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(
        name = "kakao-token-client",
        url  = "${etc.kakao-token-url}",
        configuration = com.sbungle.sbunglebe.global.config.OpenFeignFormConfig.class
)
public interface KakaoTokenClient {

    @PostMapping(consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoTokenResponse getKakaoToken(
            @RequestBody MultiValueMap<String, Object> formData
    );

}
