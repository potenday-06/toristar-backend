package com.workthis.toristar.conversation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Conversation {

    private final Long id;
    private final Long starId;
    private final String summary;

    private final LocalDateTime createdAt;

    public Conversation(Long starId, String summary) {
        this.id = null;
        this.starId = starId;
        this.summary = summary;
        this.createdAt = LocalDateTime.now();
    }
}
