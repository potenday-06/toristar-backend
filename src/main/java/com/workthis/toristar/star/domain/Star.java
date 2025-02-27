package com.workthis.toristar.star.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
public class Star {

    private final Long id;
    private final Long memberId;
    private final LocalDate createdAt;

    public Star(Long memberId) {
        this.id = null;
        this.memberId = memberId;
        this.createdAt = LocalDate.now();
    }

    public static Star createStar(Long memberId) {
        return new Star(memberId);
    }
}
