package com.workthis.toristar.conversation.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Chat {

    private final Long conversationId;
    private final ChatType type;
    private final String message;

    public static Chat createChat(ChatType type, String message) {
        return new Chat(null, type, message);
    }

}
