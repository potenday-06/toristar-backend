package com.workthis.toristar.common.dto;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccessTokenInfo {

    private final Long memberId;
}
