package com.workthis.toristar.common.exception;

public class OtherServerUnauthorizedException extends ProjectCodeException {
    public static final ProjectCodeException EXCEPTION = new OtherServerUnauthorizedException();

    private OtherServerUnauthorizedException() {
        super(GlobalErrorCode.OTHER_SERVER_UNAUTHORIZED);
    }
}
