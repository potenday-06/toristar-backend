package com.workthis.toristar.conversation.application.dto.request;

import com.workthis.toristar.chat.domain.Chat;
import com.workthis.toristar.chat.domain.ChatType;
import com.workthis.toristar.conversation.domain.Conversation;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record CreateConversationRequest(
        @Schema(description = "요약 내용") String summary,
        @Schema(description = "대화 내용 전체") List<CreateChatRequest> chats
) {

    public Conversation toConversation(Long starId) {
        return new Conversation(starId, summary);
    }

    public record CreateChatRequest(
            @Schema(description = "대화 유형") ChatType type,
            @Schema(description = "내용") String message
    ) {
        public Chat toChat(Long conversationId) {
            return new Chat(null, conversationId, type, message);
        }
    }
}
