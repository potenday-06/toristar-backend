package com.workthis.toristar.common.properties;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "auth.jwt")
public class JwtProperties {

    private Long accessExp;
    private String secretKey;
    private Long refreshExp;
    private String refreshSecretKey;
}
