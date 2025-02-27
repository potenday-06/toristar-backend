package com.workthis.toristar.member.application.service;

import com.workthis.toristar.common.utils.MemberUtils;
import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.member.application.dto.response.ReadProfileResponse;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class ReadProfileUseCase {

    private final MemberUtils memberUtils;

    public ReadProfileResponse execute() {
        return ReadProfileResponse.of(memberUtils.getCurrentMember());
    }
}
