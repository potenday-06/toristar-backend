package com.workthis.toristar.auth.application.service;

import com.workthis.toristar.auth.application.dto.AuthResponse;
import com.workthis.toristar.auth.application.service.helper.OauthHelper;
import com.workthis.toristar.auth.application.service.helper.TokenGenerateHelper;
import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.member.application.adaptor.MemberAdaptor;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.member.domain.Provider;
import com.workthis.toristar.member.repository.entity.MemberEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@UseCase
@RequiredArgsConstructor
public class RegisterOrLoginUseCase {

    private final MemberAdaptor memberAdaptor;
    private final OauthHelper oauthHelper;
    private final TokenGenerateHelper tokenGenerateHelper;

    @Transactional
    public AuthResponse execute(Provider provider, String authorizationCode) {
        // provider 별로 인가 코드를 이용해 accessToken 을 발급
        // 발급된 accessToken 으로 유저 정보를 불러옴
        String providerId = "";
        switch (provider) {
            case KAKAO:
                String kakaoAccessToken = oauthHelper.getKakaoOauthToken(authorizationCode)
                        .getAccessToken();
                providerId = oauthHelper.getKakaoUserInfo(kakaoAccessToken);
                break;
            case NAVER:
                // naver의 경우 code가 아니라 accessToken이 바로 넘어옴
                providerId = oauthHelper.getNaverUserInfo(authorizationCode);
                break;
        }
        // 유저 정보중 고유 id 으로 회원 존재하는지 조회
        Optional<MemberEntity> findMemberEntity = memberAdaptor.queryMemberByOauth(provider, providerId);
        // 존재한다면
        if (findMemberEntity.isPresent()) {
            Member member = findMemberEntity.get().toMember();
            member.login();
            member = memberAdaptor.save(member);
            String accessToken = tokenGenerateHelper.execute(member);
            return AuthResponse.of(accessToken, member.getId(), provider);
        }
        // 존재하지 않는다면 회원가입 처리
        else {
            Member newMember = Member.createMember(provider, providerId);
            newMember.login();
            newMember = memberAdaptor.save(newMember);
            String accessToken = tokenGenerateHelper.execute(newMember);
            return AuthResponse.of(accessToken, newMember.getId(), provider);
        }
    }
}
