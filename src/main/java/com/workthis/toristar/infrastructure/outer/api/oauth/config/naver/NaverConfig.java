package com.workthis.toristar.infrastructure.outer.api.oauth.config.naver;

import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@Import(NaverErrorDecoder.class)
public class NaverConfig {

    @Bean
    @ConditionalOnMissingBean(value = ErrorDecoder.class)
    public NaverErrorDecoder commonFeignErrorDecoder() {
        return new NaverErrorDecoder();
    }

    @Bean
    Encoder formEncoder() {
        return new feign.form.FormEncoder();
    }
}
