package com.workthis.toristar.star.ui;

import com.workthis.toristar.conversation.application.dto.response.ReadConversationListResponse;
import com.workthis.toristar.conversation.application.service.ReadConversationListUseCase;
import com.workthis.toristar.star.application.dto.response.ReadStarListResponse;
import com.workthis.toristar.star.application.service.ReadStarListUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "뼐 APIs")
@RestController
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
@RequestMapping("/v1/stars")
public class StarRestController {

    private final ReadStarListUseCase readStarListUseCase;
    private final ReadConversationListUseCase readConversationListUseCase;

    // 최신순으로 정렬된 우주 목록 조회
    @Operation(summary = "우리가 만든 우주(별 목록 조회)")
    @GetMapping
    public ReadStarListResponse getStars(
            @RequestParam(defaultValue = "1") @Min(1) int page
    ) {
        return readStarListUseCase.execute(page);
    }

    // 최신순으로 정렬된 이야기 목록 조회
    @Operation(summary = "이야기 목록 조회")
    @GetMapping("/{starId}/conversations")
    public ReadConversationListResponse getConversations(
            @PathVariable(name = "starId") Long starId
    ) {
        return readConversationListUseCase.execute(starId);
    }
}
