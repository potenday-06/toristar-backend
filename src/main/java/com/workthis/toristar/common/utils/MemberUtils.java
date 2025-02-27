package com.workthis.toristar.common.utils;

import com.workthis.toristar.config.security.SecurityUtils;
import com.workthis.toristar.member.application.adaptor.MemberAdaptor;
import com.workthis.toristar.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberUtils {

    private final MemberAdaptor memberAdaptor;

    public Long getCurrentMemberId() {
        return SecurityUtils.getCurrentMemberId();
    }

    public Member getCurrentMember() {
        return memberAdaptor.validateMember(getCurrentMemberId());
    }
}
