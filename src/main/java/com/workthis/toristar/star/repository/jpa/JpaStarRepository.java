package com.workthis.toristar.star.repository.jpa;

import com.workthis.toristar.star.repository.entity.StarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface JpaStarRepository extends JpaRepository<StarEntity, Long> {

    Optional<StarEntity> findStarEntityByMemberIdAndCreatedAt(Long memberId, LocalDate now);
    Optional<StarEntity> findStarEntityByIdAndMemberId(Long starId, Long memberId);
    long countAllByMemberId(Long memberId);
    List<StarEntity> findAllByMemberIdOrderByCreatedAtDesc(Long memberId, Pageable pageable);
    List<StarEntity> findAllByMemberId(Long memberId);
}
