package com.workthis.toristar.member.exception;

import com.workthis.toristar.common.exception.ProjectCodeException;

public class NotFoundMemberException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new NotFoundMemberException();

    public NotFoundMemberException() {
        super(MemberErrorCode.NOT_FOUND_MEMBER);
    }
}
