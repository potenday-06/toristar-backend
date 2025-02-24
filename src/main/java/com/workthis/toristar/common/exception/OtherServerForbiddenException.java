package com.workthis.toristar.common.exception;

public class OtherServerForbiddenException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new OtherServerForbiddenException();

    private OtherServerForbiddenException() {
        super(GlobalErrorCode.OTHER_SERVER_FORBIDDEN);
    }
}
