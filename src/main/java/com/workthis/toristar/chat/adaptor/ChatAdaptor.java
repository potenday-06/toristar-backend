package com.workthis.toristar.chat.adaptor;

import com.workthis.toristar.chat.domain.Chat;
import com.workthis.toristar.chat.repository.entity.ChatEntity;
import com.workthis.toristar.chat.repository.jpa.JpaChatRepository;
import com.workthis.toristar.common.annotation.Adaptor;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class ChatAdaptor {

    private final JpaChatRepository chatRepository;

    public List<Chat> queryChatsByConversationId(Long conversationId) {
        return chatRepository.findAllByConversationIdOrderByIdAsc(conversationId)
                .stream()
                .map(ChatEntity::toChat)
                .toList();
    }

    public void saveAll(List<Chat> chats) {
        chatRepository.saveAll(chats.stream().map(ChatEntity::new).toList());
    }
}
