package com.workthis.toristar.common.exception;

public class ExpiredTokenException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new ExpiredTokenException();

    public ExpiredTokenException() {
        super(GlobalErrorCode.TOKEN_EXPIRED);
    }
}
