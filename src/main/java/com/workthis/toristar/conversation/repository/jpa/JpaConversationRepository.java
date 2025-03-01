package com.workthis.toristar.conversation.repository.jpa;

import com.workthis.toristar.conversation.repository.entity.ConversationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaConversationRepository extends JpaRepository<ConversationEntity, Long> {

    List<ConversationEntity> findConversationEntitiesByStarIdOrderByCreatedAtDesc(Long starId);
}
