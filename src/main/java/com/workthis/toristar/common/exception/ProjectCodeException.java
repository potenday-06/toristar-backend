package com.workthis.toristar.common.exception;


import com.workthis.toristar.common.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectCodeException extends RuntimeException {

    private BaseErrorCode errorCode;

    public ErrorResponse getErrorResponse() {
        return this.errorCode.getErrorResponse();
    }
}
