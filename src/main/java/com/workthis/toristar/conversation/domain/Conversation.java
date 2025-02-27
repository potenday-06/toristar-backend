package com.workthis.toristar.conversation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class Conversation {

    private final Long id;
    private final Long starId;
    private final String summary;
    private final List<Chat> chats;

    private final LocalDateTime createdAt;

    public Conversation(Long starId, String summary, List<Chat> chat) {
        this.id = null;
        this.starId = starId;
        this.summary = summary;
        this.chats = chat;
        this.createdAt = LocalDateTime.now();
    }

    public static Conversation createConversation(Long starId, String summary, List<Chat> chats) {
        return new Conversation(starId, summary, chats);
    }
}
