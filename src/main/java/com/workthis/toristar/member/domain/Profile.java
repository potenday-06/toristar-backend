package com.workthis.toristar.member.domain;

import com.workthis.toristar.member.exception.InvalidNicknameException;
import lombok.Getter;

@Getter
public class Profile {

    private final String nickname;
    private final Integer age;
    private final Gender gender;

    public Profile() {
        this.nickname = "";
        this.age = 0;
        this.gender = Gender.NONE;
    }

    public Profile(String nickname, int age, Gender gender) {
        validateNickname(nickname);
        this.nickname = nickname;
        this.age = age;
        this.gender = gender;
    }

    private void validateNickname(String nickname) {
        if (nickname.length() > 10) {
            throw InvalidNicknameException.EXCEPTION;
        }
    }
}
