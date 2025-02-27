package com.workthis.toristar.star.exception;

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
public enum StarErrorCode implements BaseErrorCode {

    NOT_FOUND_STAR(NOT_FOUND, "STAR_404_1", "별을 찾을 수 없습니다."),
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
