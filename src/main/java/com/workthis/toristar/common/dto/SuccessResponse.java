package com.workthis.toristar.common.dto;


import lombok.Getter;

@Getter
public class SuccessResponse {

    private final boolean success = true;
    private final int status;
    private final Object data;

    public SuccessResponse(int status, Object data) {
        this.status = status;
        this.data = data;
    }
}
