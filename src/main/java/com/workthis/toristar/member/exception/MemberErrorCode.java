package com.workthis.toristar.member.exception;

import com.workthis.toristar.common.annotation.ExplainError;
import com.workthis.toristar.common.dto.ErrorResponse;
import com.workthis.toristar.common.exception.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.lang.reflect.Field;
import java.util.Objects;

import static com.workthis.toristar.common.consts.ProjectStatic.*;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    INVALID_NICKNAME(BAD_REQUEST, "MEMBER_400_1", "닉네임 유효성 검사 실패"),

    WITHDRAW_MEMBER(UNAUTHORIZED, "MEMBER_401_1", "이미 탈퇴한 회원입니다."),

    RESTRICTED_MEMBER(FORBIDDEN, "MEMBER_403_1", "제한된 회원입니다."),

    NOT_FOUND_MEMBER(NOT_FOUND, "MEMBER_404_1", "회원을 찾을 수 없습니다."),
    ;

    private final Integer status;
    private final String code;
    private final String message;

    @Override
    public ErrorResponse getErrorResponse() {
        return ErrorResponse.builder().status(status).code(code).message(message).build();
    }

    @Override
    public String getExplainError() throws NoSuchFieldException {
        Field field = this.getClass().getField(this.name());
        ExplainError annotation = field.getAnnotation(ExplainError.class);
        return Objects.nonNull(annotation) ? annotation.value() : this.getMessage();
    }
}
