package com.workthis.toristar.member.repository.jpa;

import com.workthis.toristar.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaMemberRepository extends JpaRepository<MemberEntity, Long> {
}
