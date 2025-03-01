package com.workthis.toristar.common.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties("oauth")
public class OauthProperties {

    private OAuthKakaoSecret kakao;
    private OAuthSecret naver;

    @Getter
    @Setter
    public static class OAuthSecret {
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

    @Getter
    @Setter
    public static class OAuthKakaoSecret {
        private String adminKey;
        private String appId;
        private String clientId;
        private String clientSecret;
        private String redirectUri;
    }

    public String getKakaoAdminKey() { return kakao.getAdminKey(); }

    public String getKakaoAppId() { return kakao.getAppId(); }

    public String getKakaoClientId() {
        return kakao.getClientId();
    }

    public String getKakaoClientSecret() {
        return kakao.getClientSecret();
    }

    public String getKakaoRedirectUri() {
        return kakao.getRedirectUri();
    }

    public String getNaverClientId() {
        return naver.getClientId();
    }

    public String getNaverClientSecret() {
        return naver.getClientSecret();
    }

    public String getNaverRedirectUri() {
        return naver.getRedirectUri();
    }

}
