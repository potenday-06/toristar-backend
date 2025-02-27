package com.workthis.toristar.conversation.adaptor;

import com.workthis.toristar.common.annotation.Adaptor;
import com.workthis.toristar.conversation.domain.Conversation;
import com.workthis.toristar.conversation.exception.NotFoundConversationException;
import com.workthis.toristar.conversation.repository.entity.ConversationEntity;
import com.workthis.toristar.conversation.repository.jpa.JpaConversationRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ConversationAdaptor {

    private final JpaConversationRepository conversationRepository;

    public Conversation queryConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(NotFoundConversationException::new)
                .toConversation();
    }

    public List<Conversation> queryConversationsByStarId(Long starId) {
        return conversationRepository.findConversationEntitiesByStarIdOrderByCreatedAtAsc(starId)
                .stream()
                .map(ConversationEntity::toConversation)
                .toList();
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(new ConversationEntity(conversation))
                .toConversation();
    }
}
