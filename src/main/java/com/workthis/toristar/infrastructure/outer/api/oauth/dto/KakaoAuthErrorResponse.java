package com.workthis.toristar.infrastructure.outer.api.oauth.dto;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.workthis.toristar.common.exception.OtherServerBadRequestException;
import feign.Response;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.io.InputStream;

@Getter
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class KakaoAuthErrorResponse {
    private String error;
    private String errorCode;
    private String errorDescription;

    public static KakaoAuthErrorResponse from(Response response) {
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(bodyIs, KakaoAuthErrorResponse.class);
        } catch (IOException e) {
            throw OtherServerBadRequestException.EXCEPTION;
        }
    }
}
