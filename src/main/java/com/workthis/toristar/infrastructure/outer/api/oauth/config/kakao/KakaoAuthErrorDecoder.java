package com.workthis.toristar.infrastructure.outer.api.oauth.config.kakao;

import com.workthis.toristar.common.dto.ErrorResponse;
import com.workthis.toristar.common.exception.ProjectDynamicException;
import com.workthis.toristar.infrastructure.outer.api.oauth.dto.KakaoAuthErrorResponse;
import com.workthis.toristar.infrastructure.outer.api.oauth.exception.KakaoAuthErrorCode;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KakaoAuthErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        KakaoAuthErrorResponse body = KakaoAuthErrorResponse.from(response);

        try {
            KakaoAuthErrorCode kakaoAuthErrorCode =
                    KakaoAuthErrorCode.valueOf(body.getErrorCode());
            ErrorResponse errorResponse = kakaoAuthErrorCode.getErrorResponse();
            throw new ProjectDynamicException(
                    errorResponse.getStatus(), errorResponse.getCode(), errorResponse.getMessage());
        } catch (IllegalArgumentException e) {
            KakaoAuthErrorCode koeInvalidRequest = KakaoAuthErrorCode.KOE_INVALID_REQUEST;
            ErrorResponse errorResponse = koeInvalidRequest.getErrorResponse();
            throw new ProjectDynamicException(
                    errorResponse.getStatus(), errorResponse.getCode(), errorResponse.getMessage());
        }
    }
}
