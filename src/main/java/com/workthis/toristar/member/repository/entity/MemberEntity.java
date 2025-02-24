package com.workthis.toristar.member.repository.entity;

import com.workthis.toristar.member.domain.Profile;
import com.workthis.toristar.member.domain.Provider;
import com.workthis.toristar.member.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "members")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    private String providerId;

    private LocalDateTime createdAt;

    private LocalDateTime lastLoginAt;

    public MemberEntity(Member member) {
        this.id = member.getId();
        this.nickname = member.getNickname();
        this.age = member.getAge();
        this.provider = member.getProvider();
        this.providerId = member.getProviderId();
        this.createdAt = member.getCreatedAt();
        this.lastLoginAt = member.getLastLoginAt();
    }

    public Member toMember() {
        return Member.builder()
                .id(id)
                .profile(new Profile(nickname, age))
                .provider(provider)
                .providerId(providerId)
                .createdAt(createdAt)
                .lastLoginAt(lastLoginAt)
                .build();
    }
}
