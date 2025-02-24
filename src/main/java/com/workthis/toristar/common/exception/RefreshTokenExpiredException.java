package com.workthis.toristar.common.exception;

public class RefreshTokenExpiredException extends ProjectCodeException {
    public static final ProjectCodeException EXCEPTION = new RefreshTokenExpiredException();

    private RefreshTokenExpiredException() {
        super(GlobalErrorCode.REFRESH_TOKEN_EXPIRED);
    }
}
