package com.workthis.toristar.conversation.exception;

import com.workthis.toristar.common.exception.ProjectCodeException;

public class NotFoundConversationException extends ProjectCodeException {

    public static final ProjectCodeException EXCEPTION = new NotFoundConversationException();

    public NotFoundConversationException() {
        super(ConversationErrorCode.NOT_FOUND_CONVERSATION);
    }
}
