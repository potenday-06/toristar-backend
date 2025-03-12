package com.workthis.toristar.conversation.repository.entity;

import com.workthis.toristar.conversation.domain.ConversationKeyword;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ConversationKeywordEntity {

    private String name;

    public ConversationKeywordEntity(ConversationKeyword keyword) {
        this.name = keyword.getName();
    }

    public ConversationKeyword toConversationKeyword() {
        return ConversationKeyword.builder()
                .name(name)
                .build();
    }
}
