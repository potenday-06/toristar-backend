package com.workthis.toristar.chat.repository.jpa;

import com.workthis.toristar.chat.repository.entity.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaChatRepository extends JpaRepository<ChatEntity, Long> {

    List<ChatEntity> findAllByConversationIdOrderByIdAsc(Long conversationId);
    void deleteAllByConversationIdIn(List<Long> conversationIds);
}
