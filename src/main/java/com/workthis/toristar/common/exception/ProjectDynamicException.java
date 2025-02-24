package com.workthis.toristar.common.exception;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProjectDynamicException extends RuntimeException {

    private final int status;
    private final String code;
    private final String message;
}
