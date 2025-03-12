package com.workthis.toristar.conversation.application.dto.response;

import com.workthis.toristar.conversation.domain.Conversation;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.List;

public record ReadConversationListResponse(
        @Schema(description = "선택한 별의 날짜") String createdAt,
        @Schema(description = "이야기 목록 정보") List<ReadConversationList> content
) {
    @Builder
    public record ReadConversationList(
            @Schema(description = "이야기 고유 식별자 ID") Long conversationId,
            @Schema(description = "생성한 시간 정보") Long createdAt,
            @Schema(description = "요약 정보") String summary,
            @Schema(description = "키워드") List<String> keywords
    ) {
        public static ReadConversationList of(Conversation conversation) {
            return ReadConversationList.builder()
                    .conversationId(conversation.getId())
                    .createdAt(Timestamp.valueOf(conversation.getCreatedAt()).getTime())
                    .summary(conversation.getSummary())
                    .keywords(conversation.getKeywords())
                    .build();
        }
    }
}
