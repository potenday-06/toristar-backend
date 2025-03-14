package com.workthis.toristar.conversation.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@AllArgsConstructor
public class Conversation {

    private final Long id;
    private final Long starId;
    private final String summary;
    private List<ConversationKeyword> keywords;

    private final LocalDateTime createdAt;

    public Conversation(Long starId, String summary, List<String> keywords) {
        this.id = null;
        this.starId = starId;
        this.summary = summary;
        this.keywords = keywords == null ? new ArrayList<>() : keywords.stream().map(ConversationKeyword::new).collect(Collectors.toList());
        this.createdAt = LocalDateTime.now();
    }

    public List<String> getKeywords() {
        return keywords.stream().map(ConversationKeyword::getName).toList();
    }

    public void setKeywords(List<String> keywords) {
        this.keywords = keywords.stream().map(ConversationKeyword::new).toList();
    }
}
