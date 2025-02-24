package com.workthis.toristar.member.exception;

import com.workthis.toristar.common.exception.ProjectCodeException;

public class InvalidNicknameException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new InvalidNicknameException();

    public InvalidNicknameException() {
        super(MemberErrorCode.INVALID_NICKNAME);
    }
}
