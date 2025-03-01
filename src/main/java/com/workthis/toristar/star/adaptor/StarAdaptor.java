package com.workthis.toristar.star.adaptor;

import com.workthis.toristar.common.annotation.Adaptor;
import com.workthis.toristar.conversation.adaptor.ConversationAdaptor;
import com.workthis.toristar.star.domain.Star;
import com.workthis.toristar.star.exception.NotFoundStarException;
import com.workthis.toristar.star.repository.entity.StarEntity;
import com.workthis.toristar.star.repository.jpa.JpaStarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Adaptor
@RequiredArgsConstructor
public class StarAdaptor {

    private final ConversationAdaptor conversationAdaptor;
    private final JpaStarRepository starRepository;

    public Optional<StarEntity> queryTodayStarByMemberId(Long memberId) {
        return starRepository.findStarEntityByMemberIdAndCreatedAt(memberId, LocalDate.now());
    }

    public List<Long> queryIdsByMemberId(Long memberId) {
        return starRepository.findAllByMemberId(memberId).stream()
                .map(StarEntity::getId)
                .toList();
    }

    public Star queryStarByStarIdAndMemberId(Long starId, Long memberId) {
        return starRepository.findStarEntityByIdAndMemberId(starId, memberId)
                .orElseThrow(NotFoundStarException::new)
                .toStar();
    }

    public Long queryStarCountByMemberId(Long memberId) {
        return starRepository.countAllByMemberId(memberId);
    }

    public List<Star> queryStarsByMemberIdAndPage(Long memberId, int page) {
        PageRequest pageable = PageRequest.of(page - 1, 4);
        return starRepository.findAllByMemberIdOrderByCreatedAtDesc(memberId, pageable)
            .stream()
            .map(StarEntity::toStar)
            .toList();
    }

    public Long saveStar(Star star) {
        return starRepository.save(new StarEntity(star)).getId();
    }

    public void deleteByMemberId(Long memberId) {
        List<Long> starIds = queryIdsByMemberId(memberId);
        if (!starIds.isEmpty()) {
            conversationAdaptor.deleteByStarIds(starIds);
        }
    }
}
