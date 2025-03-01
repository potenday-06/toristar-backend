package com.workthis.toristar.conversation.adaptor;

import com.workthis.toristar.chat.adaptor.ChatAdaptor;
import com.workthis.toristar.common.annotation.Adaptor;
import com.workthis.toristar.conversation.domain.Conversation;
import com.workthis.toristar.conversation.exception.NotFoundConversationException;
import com.workthis.toristar.conversation.repository.entity.ConversationEntity;
import com.workthis.toristar.conversation.repository.jpa.JpaConversationRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ConversationAdaptor {

    private final ChatAdaptor chatAdaptor;
    private final JpaConversationRepository conversationRepository;

    public Conversation queryConversationById(Long conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(NotFoundConversationException::new)
                .toConversation();
    }

    public List<Conversation> queryConversationsByStarId(Long starId) {
        return conversationRepository.findConversationEntitiesByStarIdOrderByCreatedAtDesc(starId)
                .stream()
                .map(ConversationEntity::toConversation)
                .toList();
    }

    public Conversation saveConversation(Conversation conversation) {
        return conversationRepository.save(new ConversationEntity(conversation))
                .toConversation();
    }

    public void deleteByStarIds(List<Long> starIds) {
        List<Long> conversationIds = new ArrayList<>();
        starIds.forEach(id -> conversationIds.addAll(conversationRepository.findAllByStarId(id).stream()
                .map(ConversationEntity::getId).
                toList()));
        if (!conversationIds.isEmpty()) {
            chatAdaptor.deleteAllByConversationIds(conversationIds);
        }
        conversationRepository.deleteAllByStarIdIn(starIds);
    }
}
