package com.workthis.toristar.auth.application.service.helper;

import com.workthis.toristar.common.annotation.Helper;
import com.workthis.toristar.common.jwt.JwtTokenProvider;
import com.workthis.toristar.member.domain.Member;
import lombok.RequiredArgsConstructor;

@Helper
@RequiredArgsConstructor
public class TokenGenerateHelper {

    private final JwtTokenProvider jwtTokenProvider;

    public String execute(Member member) {
        return jwtTokenProvider.generateAccessToken(member.getId());
    }
}
