package com.workthis.toristar.chat.repository.entity;

import com.workthis.toristar.chat.domain.Chat;
import com.workthis.toristar.chat.domain.ChatType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "conversation_chats")
public class ChatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long conversationId;

    @Enumerated(EnumType.STRING)
    private ChatType type;

    @Column(columnDefinition = "TEXT")
    private String message;


    public ChatEntity(Chat chat) {
        this.id = chat.getId();
        this.conversationId = chat.getConversationId();
        this.type = chat.getType();
        this.message = chat.getMessage();
    }

    public Chat toChat() {
        return Chat.builder()
                .id(id)
                .conversationId(conversationId)
                .type(type)
                .message(message)
                .build();
    }
}
