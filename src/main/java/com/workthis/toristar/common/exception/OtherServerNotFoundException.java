package com.workthis.toristar.common.exception;

public class OtherServerNotFoundException extends ProjectCodeException {
    public static final ProjectCodeException EXCEPTION = new OtherServerNotFoundException();
    public OtherServerNotFoundException() {
        super(GlobalErrorCode.OTHER_SERVER_NOT_FOUND);
    }
}
