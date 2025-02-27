package com.workthis.toristar.star.application.service;

import com.workthis.toristar.common.MemberUtils;
import com.workthis.toristar.common.annotation.UseCase;
import com.workthis.toristar.member.domain.Member;
import com.workthis.toristar.star.adaptor.StarAdaptor;
import com.workthis.toristar.star.application.dto.response.ReadStarListResponse;
import com.workthis.toristar.star.domain.Star;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@UseCase
@RequiredArgsConstructor
public class ReadStarListUseCase {

    private final StarAdaptor starAdaptor;
    private final MemberUtils memberUtils;

    public ReadStarListResponse execute(int page) {
        Member me = memberUtils.getCurrentMember();
        Long totalCount = starAdaptor.findStarCountByMemberId(me.getId());
        List<Star> content = starAdaptor.findStarsByMemberIdAndPage(me.getId(), page).stream()
                .limit(3)
                .toList();

        List<ReadStarListResponse.ReadStarList> readStars = IntStream.range(0, content.size())
                .mapToObj(i -> ReadStarListResponse.ReadStarList.of(content.get(i), (int) (totalCount - page * i)))
                .toList();
        return new ReadStarListResponse(
                totalCount,
                page == 1,
                page * 3L >= totalCount,
                readStars
        );
    }
}
