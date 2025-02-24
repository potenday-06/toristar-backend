package com.workthis.toristar.common.exception;

public class OtherServerBadRequestException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new OtherServerBadRequestException();

    private OtherServerBadRequestException() {
        super(GlobalErrorCode.OTHER_SERVER_BAD_REQUEST);
    }
}
