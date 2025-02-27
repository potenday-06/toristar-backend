package com.workthis.toristar.conversation.repository.entity;

import com.workthis.toristar.conversation.domain.Conversation;
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

    private String summary;

    @ElementCollection
    @CollectionTable(name = "conversation_chats", joinColumns = @JoinColumn(name = "conversation_id"))
    private List<ChatEntity> chats;

    private LocalDateTime createdAt;

    public ConversationEntity(Conversation conversation) {
        this.id = conversation.getId();
        this.starId = conversation.getStarId();
        this.summary = conversation.getSummary();
        this.chats = conversation.getChats().stream().map(ChatEntity::new).collect(Collectors.toList());
        this.createdAt = conversation.getCreatedAt();
    }

    public Conversation toConversation() {
        return Conversation.builder()
                .id(id)
                .starId(starId)
                .summary(summary)
                .chats(chats.stream().map(ChatEntity::toChat).collect(Collectors.toList()))
                .build();
    }
}
