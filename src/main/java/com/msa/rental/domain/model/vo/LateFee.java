package com.msa.rental.domain.model.vo;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class LateFee {

    private final long point;

    private LateFee(long point) {
        this.point = point;
    }

    public static LateFee create() {
        return new LateFee(0);
    }

    public static LateFee create(long point) {
        return new LateFee(point);
    }

    public LateFee addPoint(long point) {
        return new LateFee(this.point + point);
    }

    public LateFee removePoint(long point) {
        if (point > this.point) {
            throw new IllegalStateException("보유한 포인트보다 커서 차감할 수 없습니다.");
        }

        return new LateFee(this.point - point);
    }
}
