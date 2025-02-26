package com.workthis.toristar.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Gender {

    FEMALE("여자"),
    MALE("남자"),
    NONE("없음");

    private final String description;
}
