package com.workthis.toristar.chat.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class Chat {

    private final Long id;
    private final Long conversationId;
    private final ChatType type;
    private final String message;

    public static Chat createChat(Long conversationId, ChatType type, String message) {
        return new Chat(null, conversationId, type, message);
    }

}
