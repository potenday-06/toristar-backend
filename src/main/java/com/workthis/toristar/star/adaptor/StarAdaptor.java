package com.workthis.toristar.star.adaptor;

import com.workthis.toristar.common.annotation.Adaptor;
import com.workthis.toristar.star.domain.Star;
import com.workthis.toristar.star.repository.entity.StarEntity;
import com.workthis.toristar.star.repository.jpa.JpaStarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Adaptor
@RequiredArgsConstructor
public class StarAdaptor {

    private final JpaStarRepository starRepository;

    public Long findStarCountByMemberId(Long memberId) {
        return starRepository.countAllByMemberId(memberId);
    }

    public List<Star> findStarsByMemberIdAndPage(Long memberId, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 4);
        return starRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId, pageable)
            .stream()
            .map(StarEntity::toStar)
            .toList();
    }
}
