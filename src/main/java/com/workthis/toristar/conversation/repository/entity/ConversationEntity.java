package com.workthis.toristar.conversation.repository.entity;

import com.workthis.toristar.conversation.domain.Conversation;
import com.workthis.toristar.conversation.domain.ConversationKeyword;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "conversations")
public class ConversationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long starId;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @ElementCollection
    @CollectionTable(name = "conversation_keywords", joinColumns = @JoinColumn(name = "conversation_id"))
    private List<ConversationKeywordEntity> keywords;

    private LocalDateTime createdAt;

    public ConversationEntity(Conversation conversation) {
        this.id = conversation.getId();
        this.starId = conversation.getStarId();
        this.summary = conversation.getSummary();
        this.keywords = conversation.getKeywords().stream().map(ConversationKeywordEntity::new).toList();
        this.createdAt = conversation.getCreatedAt();
    }

    public Conversation toConversation() {
        return Conversation.builder()
                .id(id)
                .starId(starId)
                .summary(summary)
                .keywords(keywords.stream().map(ConversationKeywordEntity::toConversationKeyword).collect(Collectors.toList()))
                .createdAt(createdAt)
                .build();
    }
}
