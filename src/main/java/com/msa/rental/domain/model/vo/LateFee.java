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

    public LateFee accumulate(long point) {
        return new LateFee(this.point + point);
    }

    public long getRemainingPointsAfterDeduction(long deductedPoint) {
        if (deductedPoint <= this.point) {
            return 0;
        }

        return deductedPoint - this.point;
    }

    public LateFee deduct(long point) {
        return new LateFee(this.point - point);
    }

    public boolean isEmpty() {
        return point == 0;
    }
}
