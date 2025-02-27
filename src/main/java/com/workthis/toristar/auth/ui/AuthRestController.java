package com.workthis.toristar.auth.ui;

import com.workthis.toristar.auth.application.dto.response.AuthResponse;
import com.workthis.toristar.auth.application.service.RegisterOrLoginUseCase;
import com.workthis.toristar.member.domain.Provider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseCookie;
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
            @Schema(description = "각 플랫폼에서 받은 인가코드") @RequestParam(name = "code") String code,
            HttpServletResponse response
    ) {
        AuthResponse authResponse = registerOrLoginUseCase.execute(Provider.fromString(provider), code);
        ResponseCookie accessTokenCookie = ResponseCookie.from("accessToken", authResponse.accessToken()) // 토큰 값 설정
                .httpOnly(true)  // JavaScript에서 접근 불가능
                .secure(true)    // HTTPS 환경에서만 전송
                .sameSite("None") // CSRF 방어
                .path("/")       // 모든 경로에서 쿠키 적용
                .build();
        response.addHeader("Set-Cookie", accessTokenCookie.toString());
        return authResponse;
    }

}
