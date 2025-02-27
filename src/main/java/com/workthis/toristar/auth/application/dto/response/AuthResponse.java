package com.workthis.toristar.auth.application.dto.response;

import com.workthis.toristar.member.domain.Provider;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Builder
public record AuthResponse(
    @Schema(description = "만료일 24시간", example = "member_access_token")
    String accessToken,
    @Schema(description = "회원 ID", example = "1")
    Long memberId,
    @Schema(description = "플랫폼", example = "KAKAO")
    Provider provider
) {
    public static AuthResponse of(String accessToken, Long memberId, Provider provider) {
        return AuthResponse.builder()
                .accessToken(accessToken)
                .memberId(memberId)
                .provider(provider)
                .build();
    }
}
