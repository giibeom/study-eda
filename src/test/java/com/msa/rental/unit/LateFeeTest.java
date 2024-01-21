package com.msa.rental.unit;

import com.msa.rental.domain.model.vo.LateFee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class LateFeeTest {

    @Test
    @DisplayName("1000 연체 포인트에서 1000 포인트를 추가로 적립할 경우, 2000포인트로 반환한다.")
    void addPoint() {
        LateFee lateFee = LateFee.create(1000);

        assertThat(lateFee.accumulate(1000)).isEqualTo(LateFee.create(2000));
    }

    @Test
    @DisplayName("1000 연체 포인트에서 500 포인트를 차감할 경우, 500포인트로 반환한다.")
    void removePoint() {
        LateFee lateFee = LateFee.create(1000);

        assertThat(lateFee.deduct(500)).isEqualTo(LateFee.create(500));
    }

    @Test
    @DisplayName("1000 연체 포인트에서 1500 포인트를 차감 후 남은 포인트는 500포인트를 반환한다.")
    void getRemainingPointsAfterDeduction() {
        LateFee lateFee = LateFee.create(1000);

        assertThat(lateFee.getRemainingPointsAfterDeduction(1500)).isEqualTo(500);
    }
}
