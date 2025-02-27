package com.workthis.toristar.member.application.dto.response;

import com.workthis.toristar.member.domain.Gender;
import com.workthis.toristar.member.domain.Member;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReadProfileResponse(
        String nickname,
        int age,
        Gender gender,
        LocalDateTime createdAt
) {

    public static ReadProfileResponse of(Member member) {
        return ReadProfileResponse.builder()
                .nickname(member.getNickname())
                .age(member.getAge())
                .gender(member.getGender())
                .createdAt(member.getCreatedAt())
                .build();
    }
}
