package com.workthis.toristar.auth.application.service.helper;


import com.workthis.toristar.common.annotation.Helper;
import com.workthis.toristar.common.properties.OauthProperties;
import com.workthis.toristar.infrastructure.outer.api.oauth.client.KakaoOauthClient;
import com.workthis.toristar.infrastructure.outer.api.oauth.client.KakaoUserInfoClient;
import com.workthis.toristar.infrastructure.outer.api.oauth.client.NaverUserInfoClient;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.KakaoInformationResponse;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.KakaoTokenResponse;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.NaverInfoResponse;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.NaverTokenResponse;
import lombok.RequiredArgsConstructor;

import static com.workthis.toristar.common.consts.ProjectStatic.BEARER;

@Helper
@RequiredArgsConstructor
public class OauthHelper {

    private final OauthProperties oauthProperties;

    private final KakaoUserInfoClient kakaoUserInfoClient;
    private final KakaoOauthClient kakaoOauthClient;

    private final NaverUserInfoClient naverUserInfoClient;

    public KakaoTokenResponse getKakaoOauthToken(String code) {
        return kakaoOauthClient.kakaoAuth(
                oauthProperties.getKakaoClientId(),
                oauthProperties.getKakaoRedirectUri(),
                code,
                oauthProperties.getKakaoClientSecret());
    }

    public String getKakaoUserInfo(String oauthAccessToken) {
        KakaoInformationResponse response = kakaoUserInfoClient.kakaoUserInfo(BEARER + oauthAccessToken);

        return response.getId();
    }

    public String getNaverUserInfo(String oauthAccessToken) {
        NaverInfoResponse response = naverUserInfoClient.naverUserInfo(BEARER + oauthAccessToken);

        return response.getResponse().getId();
    }
}
