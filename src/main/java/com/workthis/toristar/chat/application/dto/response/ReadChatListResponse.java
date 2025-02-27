package com.workthis.toristar.chat.application.dto.response;

import com.workthis.toristar.chat.domain.Chat;
import com.workthis.toristar.chat.domain.ChatType;
import lombok.Builder;

import java.util.List;

public record ReadChatListResponse(
        List<ReadChatList> content,
        Long createdAt
) {

    @Builder
    public record ReadChatList(
            ChatType type,
            String message
    ) {
        public static ReadChatList of(Chat chat) {
            return ReadChatList.builder()
                    .type(chat.getType())
                    .message(chat.getMessage())
                    .build();
        }
    }
}
