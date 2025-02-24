package com.workthis.toristar.infrastructure.outer.api.oauth.client;

import com.workthis.toristar.infrastructure.outer.api.oauth.config.kakao.KakaoInfoConfig;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.KakaoInformationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "KakaoInfoClient",
        url = "https://kapi.kakao.com",
        configuration = KakaoInfoConfig.class)
public interface KakaoUserInfoClient {

    @GetMapping("/v2/user/me")
    KakaoInformationResponse kakaoUserInfo(@RequestHeader("Authorization") String accessToken);
}
