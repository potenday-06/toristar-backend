package com.workthis.toristar.common.exception;

public class InvalidTokenException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(GlobalErrorCode.INVALID_TOKEN);
    }
}
