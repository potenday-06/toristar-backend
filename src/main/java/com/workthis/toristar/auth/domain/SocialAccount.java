package com.workthis.toristar.auth.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class SocialAccount {

    private final Long id;
    private final Long memberId;
    private final Provider provider;
    private final String providerId;

    private final LocalDateTime createdAt;
    private LocalDateTime lastLoginAt;

    public static SocialAccount createSocialAccount(Long memberId, Provider provider, String providerId) {
        return new SocialAccount(null, memberId, provider, providerId, LocalDateTime.now(), null);
    }

    public void login() {
        this.lastLoginAt = LocalDateTime.now();
    }
}