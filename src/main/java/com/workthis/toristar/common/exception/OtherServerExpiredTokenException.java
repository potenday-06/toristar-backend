package com.workthis.toristar.common.exception;

public class OtherServerExpiredTokenException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new OtherServerExpiredTokenException();

    private OtherServerExpiredTokenException() {
        super(GlobalErrorCode.OTHER_SERVER_EXPIRED_TOKEN);
    }
}
