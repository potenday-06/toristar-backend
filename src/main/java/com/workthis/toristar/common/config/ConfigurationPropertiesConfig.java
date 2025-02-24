package com.workthis.toristar.common.config;


import com.workthis.toristar.common.properties.JwtProperties;
import com.workthis.toristar.common.properties.OauthProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@EnableConfigurationProperties({OauthProperties.class, JwtProperties.class})
@Configuration
public class ConfigurationPropertiesConfig {}
