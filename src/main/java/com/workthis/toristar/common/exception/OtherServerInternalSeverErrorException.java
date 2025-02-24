package com.workthis.toristar.common.exception;

public class OtherServerInternalSeverErrorException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION =
            new OtherServerInternalSeverErrorException();

    private OtherServerInternalSeverErrorException() {
        super(GlobalErrorCode.OTHER_SERVER_INTERNAL_SERVER_ERROR);
    }
}
