package com.workthis.toristar.conversation.repository.entity;

import com.workthis.toristar.conversation.domain.Conversation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    private LocalDateTime createdAt;

    public ConversationEntity(Conversation conversation) {
        this.id = conversation.getId();
        this.starId = conversation.getStarId();
        this.summary = conversation.getSummary();
        this.createdAt = conversation.getCreatedAt();
    }

    public Conversation toConversation() {
        return Conversation.builder()
                .id(id)
                .starId(starId)
                .summary(summary)
                .createdAt(createdAt)
                .build();
    }
}
