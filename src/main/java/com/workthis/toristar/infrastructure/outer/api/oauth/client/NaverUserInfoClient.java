package com.workthis.toristar.infrastructure.outer.api.oauth.client;

import com.workthis.toristar.infrastructure.outer.api.oauth.config.naver.NaverConfig;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.NaverInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
        name = "NaverInfoClient",
        url = "https://openapi.naver.com",
        configuration = NaverConfig.class)
public interface NaverUserInfoClient {

    @GetMapping("/v1/nid/me")
    NaverInfoResponse naverUserInfo(@RequestHeader("Authorization") String accessToken);
}
