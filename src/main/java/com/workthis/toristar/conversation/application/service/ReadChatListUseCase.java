package com.workthis.toristar.conversation.application.service;

import com.workthis.toristar.chat.adaptor.ChatAdaptor;
import com.workthis.toristar.chat.application.dto.response.ReadChatListResponse;
import com.workthis.toristar.chat.domain.Chat;
import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.common.utils.MemberUtils;
import com.workthis.toristar.conversation.adaptor.ConversationAdaptor;
import com.workthis.toristar.conversation.domain.Conversation;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@UseCase
@RequiredArgsConstructor
public class ReadChatListUseCase {

    private final MemberUtils memberUtils;
    private final ConversationAdaptor conversationAdaptor;
    private final ChatAdaptor chatAdaptor;

    @Transactional(readOnly = true)
    public ReadChatListResponse execute(Long conversationId) {
        memberUtils.getCurrentMember();
        Conversation conversation = conversationAdaptor.queryConversationById(conversationId);
        List<Chat> chats = chatAdaptor.queryChatsByConversationId(conversationId);
        return new ReadChatListResponse(
                chats.stream().map(ReadChatListResponse.ReadChatList::of).toList(),
                Timestamp.valueOf(conversation.getCreatedAt()).getTime()
        );
    }
}
