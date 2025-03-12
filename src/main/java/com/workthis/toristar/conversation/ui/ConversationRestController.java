package com.workthis.toristar.conversation.ui;

import com.workthis.toristar.chat.application.dto.response.ReadChatListResponse;
import com.workthis.toristar.conversation.application.dto.request.CreateConversationRequest;
import com.workthis.toristar.conversation.application.dto.request.UpdateConversationKeywordRequest;
import com.workthis.toristar.conversation.application.service.CreateConversationUseCase;
import com.workthis.toristar.conversation.application.service.ReadChatListUseCase;
import com.workthis.toristar.conversation.application.service.UpdateConversationKeywordUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "대화 APIs")
@RestController
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
@RequestMapping("/v1/conversations")
public class ConversationRestController {

    private final ReadChatListUseCase readChatListUseCase;
    private final CreateConversationUseCase createConversationUseCase;
    private final UpdateConversationKeywordUseCase updateConversationKeywordUseCase;

    @Operation(summary = "대화내용 전체보기")
    @GetMapping("/{conversationId}/chats")
    public ReadChatListResponse getChats(
            @PathVariable("conversationId") Long conversationId
    ) {
        return readChatListUseCase.execute(conversationId);
    }

    @Operation(summary = "대화내용 저장")
    @PostMapping
    public void setConversation(
            @RequestBody CreateConversationRequest request
    ) {
        createConversationUseCase.execute(request);
    }

    @Operation(summary = "키워드 저장")
    @PatchMapping("/{conversationId}/keywords")
    public void setKeywords(
            @PathVariable("conversationId") Long conversationId,
            @RequestBody UpdateConversationKeywordRequest request
    ) {
        updateConversationKeywordUseCase.execute(conversationId, request);
    }
}
