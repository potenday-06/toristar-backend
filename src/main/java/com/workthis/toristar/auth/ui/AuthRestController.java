package com.workthis.toristar.auth.ui;

import com.workthis.toristar.auth.application.dto.AuthResponse;
import com.workthis.toristar.auth.application.service.RegisterOrLoginUseCase;
import com.workthis.toristar.member.domain.Provider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Tag(name = "인증 APIs")
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/auth")
public class AuthRestController {

    private final RegisterOrLoginUseCase registerOrLoginUseCase;

    // code 를 통해 회원가입 or 로그인 API
    @Operation(summary = "인가 코드를 통해 각 플랫폼 회원가입 or 로그인 API")
    @GetMapping("/provider/{provider}")
    public AuthResponse registerOrLogin(
            @Schema(description = "카카오: kakao, 네이버: naver") @PathVariable(name = "provider") String provider,
            @Schema(description = "각 플랫폼에서 받은 인가코드") @RequestParam(name = "code") String code
    ) {
        return registerOrLoginUseCase.execute(Provider.fromString(provider), code);
    }

}
