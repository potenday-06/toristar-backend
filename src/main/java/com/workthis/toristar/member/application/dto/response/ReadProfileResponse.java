package com.workthis.toristar.member.application.dto.response;

import com.workthis.toristar.member.domain.Gender;
import com.workthis.toristar.member.domain.Member;
import lombok.Builder;

@Builder
public record ReadProfileResponse(
        String nickname,
        int age,
        Gender gender
) {

    public static ReadProfileResponse of(Member member) {
        return ReadProfileResponse.builder()
                .nickname(member.getNickname())
                .age(member.getAge())
                .gender(member.getGender())
                .build();
    }
}
