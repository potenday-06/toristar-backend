package com.workthis.toristar.common.dto;


import com.workthis.toristar.common.exception.GlobalErrorCode;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ErrorResponse {

    private final boolean success = false;
    private final int status;
    private final String code;
    private final String message;
    private final List<CustomFieldError> errors;

    public ErrorResponse(GlobalErrorCode code) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.errors = null;
    }

    public ErrorResponse(GlobalErrorCode code, List<CustomFieldError> errors) {
        this.status = code.getStatus();
        this.code = code.getCode();
        this.message = code.getMessage();
        this.errors = errors;
    }

    public ErrorResponse(int status, String code, String message, List<CustomFieldError> errors) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.errors = errors;
    }

    public ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
        this.errors = null;
    }
}
