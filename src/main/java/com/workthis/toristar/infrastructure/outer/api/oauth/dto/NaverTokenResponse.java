package com.workthis.toristar.infrastructure.outer.api.oauth.dto;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.fasterxml.jackson.databind.PropertyNamingStrategies.*;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class NaverTokenResponse {

    private String accessToken;
    private String refreshToken;
}
