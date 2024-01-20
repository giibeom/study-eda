package com.msa.rental.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class LateFeeTest {

    @Test
    @DisplayName("1000 연체 포인트에서 1000 포인트를 추가로 적립할 경우, 2000포인트로 반환한다.")
    void addPoint() {
        LateFee lateFee = LateFee.create(1000);

        assertThat(lateFee.addPoint(1000)).isEqualTo(LateFee.create(2000));
    }

    @Test
    @DisplayName("1000 연체 포인트에서 500 포인트를 차감할 경우, 500포인트로 반환한다.")
    void validRemovePoint() {
        LateFee lateFee = LateFee.create(1000);

        assertThat(lateFee.removePoint(500)).isEqualTo(LateFee.create(500));
    }

    @Test
    @DisplayName("기존 연체 포인트 이상의 포인트를 차감하려고 할 경우, 예외가 발생한다.")
    void invalidRemovePoint() {
        LateFee lateFee = LateFee.create(1000);

        assertThatThrownBy(() -> lateFee.removePoint(2000))
                .isInstanceOf(IllegalStateException.class);
    }
}
