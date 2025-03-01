package com.workthis.toristar.member.application.adaptor;

import com.workthis.toristar.common.annotation.Adaptor;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.member.domain.Provider;
import com.workthis.toristar.member.exception.NotFoundMemberException;
import com.workthis.toristar.member.repository.entity.MemberEntity;
import com.workthis.toristar.member.repository.jpa.JpaMemberRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Adaptor
@RequiredArgsConstructor
public class MemberAdaptor {

    private final JpaMemberRepository memberRepository;

    public Optional<MemberEntity> queryMemberByOauth(Provider provider, String providerId) {
        return memberRepository.findMemberEntityByProviderAndProviderId(provider, providerId);
    }

    public Member save(Member member) {
        return memberRepository.save(new MemberEntity(member)).toMember();
    }

    public Member validateMember(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> NotFoundMemberException.EXCEPTION)
                .toMember();
    }

    public void deleteByProviderId(String providerId) {
        memberRepository.deleteByProviderId(providerId);
    }
}
