package com.workthis.toristar.star.repository.entity;

import com.workthis.toristar.star.domain.Star;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Table(name = "stars")
public class StarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;

    private LocalDate createdAt;

    public StarEntity(Star star) {
        this.id = star.getId();
        this.memberId = star.getMemberId();
        this.createdAt = star.getCreatedAt();
    }

    public Star toStar() {
        return Star.builder()
                .id(id)
                .memberId(memberId)
                .createdAt(createdAt)
                .build();
    }
}
