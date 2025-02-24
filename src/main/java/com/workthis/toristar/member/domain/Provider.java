package com.workthis.toristar.member.domain;

import com.workthis.toristar.auth.exception.InvalidProviderException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Provider {

    KAKAO,
    NAVER;

    public static Provider fromString(String value) {
        try {
            return Provider.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw InvalidProviderException.EXCEPTION;
        }
    }
}
