package com.workthis.toristar.conversation.application.service;

import com.workthis.toristar.chat.adaptor.ChatAdaptor;
import com.workthis.toristar.chat.domain.Chat;
import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.common.utils.MemberUtils;
import com.workthis.toristar.conversation.adaptor.ConversationAdaptor;
import com.workthis.toristar.conversation.application.dto.request.CreateConversationRequest;
import com.workthis.toristar.conversation.domain.Conversation;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.star.adaptor.StarAdaptor;
import com.workthis.toristar.star.domain.Star;
import com.workthis.toristar.star.repository.entity.StarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class CreateConversationUseCase {

    private final MemberUtils memberUtils;
    private final StarAdaptor starAdaptor;
    private final ConversationAdaptor conversationAdaptor;
    private final ChatAdaptor chatAdaptor;

    @Transactional
    public void execute(CreateConversationRequest request) {
        Member me = memberUtils.getCurrentMember();
        // 오늘 날짜 기준으로 star 가 존재하는지 체크
        Optional<StarEntity> starEntity = starAdaptor.queryTodayStarByMemberId(me.getId());
        // star 존재하지 않으면 새로운 star 를 생성
        Long starId = starEntity.map(StarEntity::getId)
            .orElseGet(() -> starAdaptor.saveStar(Star.createStar(me.getId())));

        Conversation newConversation = conversationAdaptor.saveConversation(request.toConversation(starId));
        List<Chat> chats = request.chats().stream()
                .map(c -> c.toChat(newConversation.getId()))
                .toList();
        chatAdaptor.saveAll(chats);
    }
}
