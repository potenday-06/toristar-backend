package com.workthis.toristar.common.exception;

public class SecurityContextNotFoundException extends ProjectCodeException {
    public static final ProjectCodeException EXCEPTION = new SecurityContextNotFoundException();

    private SecurityContextNotFoundException() {
        super(GlobalErrorCode.SECURITY_CONTEXT_NOT_FOUND);
    }
}
