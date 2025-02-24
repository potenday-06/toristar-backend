package com.workthis.toristar.common.exception;

public class NotFoundException extends ProjectCodeException {
    public static final ProjectCodeException EXCEPTION = new NotFoundException();

    public NotFoundException() {
        super(GlobalErrorCode.NOT_FOUND_DATA);
    }
}
