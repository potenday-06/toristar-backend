package com.workthis.toristar.infrastructure.outer.api.oauth.client;

import com.workthis.toristar.infrastructure.outer.api.oauth.config.naver.NaverConfig;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.NaverTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(
        name = "NaverAuthClient",
        url = "https://nid.naver.com",
        configuration = NaverConfig.class)
public interface NaverOauthClient {

    @PostMapping(
            "/oauth2.0/token?grant_type=authorization_code&client_id={CLIENT_ID}&code={CODE}&client_secret={CLIENT_SECRET}"
    )
    NaverTokenResponse naverAuth(
            @PathVariable("CLIENT_ID") String clientId,
            @PathVariable("CODE") String code,
            @PathVariable("CLIENT_SECRET") String client_secret
    );
}
