package com.workthis.toristar.auth.application.service;

import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.common.properties.OauthProperties;
import com.workthis.toristar.member.application.adaptor.MemberAdaptor;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.member.domain.Provider;
import com.workthis.toristar.member.exception.NotFoundMemberException;
import com.workthis.toristar.star.adaptor.StarAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class LeaveKakaoMemberUseCase {

    private final MemberAdaptor memberAdaptor;
    private final StarAdaptor starAdaptor;
    private final OauthProperties oauthProperties;

    @Transactional
    public void execute(String adminKey, String appId, String userId, String referrerType) {
        if (oauthProperties.getKakaoAdminKey().equals(adminKey) && oauthProperties.getKakaoAppId().equals(appId)) {
            Member me = memberAdaptor.queryMemberByOauth(Provider.KAKAO, userId)
                    .orElseThrow(NotFoundMemberException::new)
                    .toMember();
            memberAdaptor.deleteByProviderId(me.getProviderId());

            starAdaptor.deleteByMemberId(me.getId());
        }
    }
}
