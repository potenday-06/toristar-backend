package com.workthis.toristar.member.repository.jpa;

import com.workthis.toristar.member.domain.Provider;
import com.workthis.toristar.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {

    Optional<MemberEntity> findMemberEntityByProviderAndProviderId(Provider provider, String providerId);

    void deleteByProviderId(String providerId);
}
