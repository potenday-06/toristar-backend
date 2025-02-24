package com.workthis.toristar.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class Member {

    private final Long id;
    private final Profile profile;
    private final Provider provider;
    private final String providerId;

    private final LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    public static Member createMember(Provider provider, String providerId) {
        return new Member(null, new Profile(), provider, providerId, LocalDateTime.now(), null);
    }

    public String getNickname() {
        return profile.getNickname();
    }

    public int getAge() {
        return profile.getAge();
    }

    public void login() {
        this.lastLoginAt = LocalDateTime.now();
    }
}
