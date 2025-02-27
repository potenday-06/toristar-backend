package com.workthis.toristar.member.application.service;

import com.workthis.toristar.common.utils.MemberUtils;
import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.member.application.adaptor.MemberAdaptor;
import com.workthis.toristar.member.application.dto.request.CreateMemberRequest;
import com.workthis.toristar.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@RequiredArgsConstructor
public class CreateProfileUseCase {

    private final MemberAdaptor memberAdaptor;
    private final MemberUtils memberUtils;

    @Transactional
    public void execute(CreateMemberRequest request) {
        Member me = memberUtils.getCurrentMember();
        me.setProfile(
                request.nickname(),
                request.age(),
                request.gender()
        );
        memberAdaptor.save(me);
    }
}
