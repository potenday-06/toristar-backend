package com.workthis.toristar.star.exception;

import com.workthis.toristar.common.exception.ProjectCodeException;

public class NotFoundStarException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new NotFoundStarException();

    public NotFoundStarException() {
        super(StarErrorCode.NOT_FOUND_STAR);
    }
}
