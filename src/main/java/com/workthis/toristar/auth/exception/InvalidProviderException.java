package com.workthis.toristar.auth.exception;

import com.workthis.toristar.common.exception.ProjectCodeException;
import com.workthis.toristar.member.exception.MemberErrorCode;

public class InvalidProviderException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new InvalidProviderException();

    public InvalidProviderException() {
        super(AuthErrorCode.INVALID_PROVIDER);
    }
}
