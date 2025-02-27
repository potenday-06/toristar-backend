package com.workthis.toristar.conversation.repository.entity;

import com.workthis.toristar.conversation.domain.Chat;
import com.workthis.toristar.conversation.domain.ChatType;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChatEntity {

    private ChatType type;
    private String message;

    public ChatEntity(Chat chat) {
        this.type = chat.getType();
        this.message = chat.getMessage();
    }

    public Chat toChat() {
        return Chat.builder()
                .type(type)
                .message(message)
                .build();
    }
}
