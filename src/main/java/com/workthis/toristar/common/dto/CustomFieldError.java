package com.workthis.toristar.common.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.validation.FieldError;

@Getter
@Builder
@AllArgsConstructor
public class CustomFieldError {

    private final String param;
    private final Object value;
    private final String error;
    private final String msg;

    public CustomFieldError(FieldError fieldError) {
        this.param = fieldError.getField();
        this.value = fieldError.getRejectedValue();
        this.error = fieldError.getCode();
        this.msg = fieldError.getDefaultMessage();
    }
}
