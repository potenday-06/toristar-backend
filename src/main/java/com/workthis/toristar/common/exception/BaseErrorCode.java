package com.workthis.toristar.common.exception;


import com.workthis.toristar.common.dto.ErrorResponse;

public interface BaseErrorCode {

    public ErrorResponse getErrorResponse();

    String getExplainError() throws NoSuchFieldException;
}
