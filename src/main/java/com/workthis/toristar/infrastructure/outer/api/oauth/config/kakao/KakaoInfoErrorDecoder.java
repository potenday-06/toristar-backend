package com.workthis.toristar.infrastructure.outer.api.oauth.config.kakao;

import com.workthis.toristar.common.exception.*;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class KakaoInfoErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) throws ProjectCodeException {
        if (response.status() >= 400) {
            switch (response.status()) {
                case 401:
                    throw OtherServerUnauthorizedException.EXCEPTION;
                case 403:
                    throw OtherServerForbiddenException.EXCEPTION;
                case 419:
                    throw OtherServerExpiredTokenException.EXCEPTION;
                default:
                    throw OtherServerBadRequestException.EXCEPTION;
            }
        }

        return FeignException.errorStatus(methodKey, response);
    }
}
