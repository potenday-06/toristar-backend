package com.workthis.toristar.conversation.application.service;

import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.common.utils.DateConverter;
import com.workthis.toristar.common.utils.MemberUtils;
import com.workthis.toristar.conversation.adaptor.ConversationAdaptor;
import com.workthis.toristar.conversation.application.dto.response.ReadConversationListResponse;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.star.adaptor.StarAdaptor;
import com.workthis.toristar.star.domain.Star;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReadConversationListUseCase {

    private final MemberUtils memberUtils;
    private final StarAdaptor starAdaptor;
    private final ConversationAdaptor conversationAdaptor;

    public ReadConversationListResponse execute(Long starId) {
        Member me = memberUtils.getCurrentMember();
        Star star = starAdaptor.queryStarByStarIdAndMemberId(starId, me.getId());

        return new ReadConversationListResponse(
                DateConverter.formatDateWithDay(star.getCreatedAt()),
                conversationAdaptor.queryConversationsByStarId(starId).stream()
                        .map(ReadConversationListResponse.ReadConversationList::of)
                        .toList()
        );
    }
}
