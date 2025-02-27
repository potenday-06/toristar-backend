package com.workthis.toristar.star.application.dto.response;

import com.workthis.toristar.common.utils.DateConverter;
import com.workthis.toristar.star.domain.Star;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.List;

public record ReadStarListResponse(
        @Schema(description = "총 개수") Long totalCount,
        @Schema(description = "첫 번째 페이지 여부") boolean isFirst,
        @Schema(description = "마지막 페이지 여부") boolean isLast,
        @Schema(description = "별 목록 정보") List<ReadStarList> content
) {
    @Builder
    public record ReadStarList(
            @Schema(description = "별 고유 식별자 ID") Long starId,
            @Schema(description = "몇 번째 별") String name,
            @Schema(description = "생성일") String createdAt
    ) {
        public static ReadStarList of(Star star, int index) {
            return ReadStarList.builder()
                    .starId(star.getId())
                    .name(index + "번째 별")
                    .createdAt(DateConverter.convertToRelativeDate(star.getCreatedAt()))
                    .build();
        }
    }
}
