package com.workthis.toristar.member.application.dto.request;

import com.workthis.toristar.common.annotation.Enum;
import com.workthis.toristar.member.domain.Gender;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

public record CreateMemberRequest(
        @Schema(description = "회원 이름", example = "서영")
        String nickname,
        @Schema(description = "회원 나이", example = "10")
        int age,
        @Schema(description = "회원 성별, FEMALE 여자, MALE 남자", example = "MALE")
        @Enum
        @Parameter(required = true)
        Gender gender
) {

}
