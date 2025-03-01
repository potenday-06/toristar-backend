package com.workthis.toristar.auth.application.service;

import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.common.utils.NaverEncryptUtils;
import com.workthis.toristar.member.application.adaptor.MemberAdaptor;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.member.domain.Provider;
import com.workthis.toristar.member.exception.NotFoundMemberException;
import com.workthis.toristar.star.adaptor.StarAdaptor;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;


@UseCase
@RequiredArgsConstructor
public class LeaveNaverMemberUseCase {

    private final MemberAdaptor memberAdaptor;
    private final StarAdaptor starAdaptor;
    private final NaverEncryptUtils naverEncryptUtils;

    @Transactional
    public void execute(String clientId, String encryptUniqueId, String timestamp, String signature) throws Exception {
        System.out.println("clientId = " + clientId);
        System.out.println("encryptUniqueId = " + encryptUniqueId);
        System.out.println("timestamp = " + timestamp);
        System.out.println("signature = " + signature);
        if (naverEncryptUtils.validateNaverHmac(clientId, encryptUniqueId, timestamp, signature)) {
            String decryptNaverProviderId = naverEncryptUtils.decryptNaverProviderId(encryptUniqueId);
            Member me = memberAdaptor.queryMemberByOauth(Provider.NAVER, decryptNaverProviderId)
                    .orElseThrow(NotFoundMemberException::new)
                    .toMember();
            memberAdaptor.deleteByProviderId(me.getProviderId());

            starAdaptor.deleteByMemberId(me.getId());
        }
    }



}
