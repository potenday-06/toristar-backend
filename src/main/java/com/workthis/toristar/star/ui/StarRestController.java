package com.workthis.toristar.star.ui;

import com.workthis.toristar.star.application.dto.response.ReadStarListResponse;
import com.workthis.toristar.star.application.service.ReadStarListUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "우주 APIs")
@RestController
@SecurityRequirement(name = "access-token")
@RequiredArgsConstructor
@RequestMapping("/v1/universes")
public class StarRestController {

    private final ReadStarListUseCase readStarListUseCase;

    // 최신순으로 정렬된 우주 목록 조회
    @Operation(summary = "우리가 만든 우주")
    @GetMapping
    public ReadStarListResponse getUniverses(
            @RequestParam(defaultValue = "1") @Min(1) int page
    ) {
        return readStarListUseCase.execute(page);
    }


}
