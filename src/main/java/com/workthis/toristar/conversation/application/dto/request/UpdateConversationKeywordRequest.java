package com.workthis.toristar.conversation.application.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record UpdateConversationKeywordRequest(
        @Schema(description = "키워드") List<String> keywords
) {
}
