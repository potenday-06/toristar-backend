package com.workthis.toristar.member.domain;

import com.workthis.toristar.member.exception.InvalidNicknameException;
import lombok.Getter;

@Getter
public class Profile {

    private final String nickname;
    private final Integer age;

    public Profile() {
        this.nickname = "";
        this.age = 0;
    }

    public Profile(String nickname, int age) {
        validateNickname(nickname);
        this.nickname = nickname;
        this.age = age;
    }

    private void validateNickname(String nickname) {
        if (nickname.length() > 10) {
            throw InvalidNicknameException.EXCEPTION;
        }
    }
}
