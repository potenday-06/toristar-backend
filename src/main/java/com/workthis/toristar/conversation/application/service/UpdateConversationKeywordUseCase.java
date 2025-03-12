package com.workthis.toristar.conversation.application.service;

import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.common.utils.MemberUtils;
import com.workthis.toristar.conversation.adaptor.ConversationAdaptor;
import com.workthis.toristar.conversation.application.dto.request.UpdateConversationKeywordRequest;
import com.workthis.toristar.conversation.domain.Conversation;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.star.adaptor.StarAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class UpdateConversationKeywordUseCase {

    private final MemberUtils memberUtils;
    private final ConversationAdaptor conversationAdaptor;
    private final StarAdaptor starAdaptor;

    @Transactional
    public void execute(Long conversationId, UpdateConversationKeywordRequest request) {
        Member me = memberUtils.getCurrentMember();
        Conversation findConversation = conversationAdaptor.queryConversationById(conversationId);
        // 본인 식별자와 요청한 이야기의 별 식별자로 본인 이야기가 맞는지 검증
        starAdaptor.queryStarByStarIdAndMemberId(findConversation.getStarId(), me.getId());
        findConversation.setKeywords(request.keywords());
        conversationAdaptor.saveConversation(findConversation);
    }
}
