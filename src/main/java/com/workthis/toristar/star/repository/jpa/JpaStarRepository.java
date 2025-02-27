package com.workthis.toristar.star.repository.jpa;

import com.workthis.toristar.star.repository.entity.StarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaStarRepository extends JpaRepository<StarEntity, Long> {

    long countAllByMemberId(Long memberId);
    List<StarEntity> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
}
