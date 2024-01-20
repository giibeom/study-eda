package com.msa.rental.domain.model.vo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class RentalCardNoTest {

    @Test
    @DisplayName("대여카드 번호를 새로 발급할 경우, 맨 앞의 prefix는 현재의 연도이다.")
    void createRentalCardNo() {
        RentalCardNo rentalCardNo = RentalCardNo.create();
        String yearPrefix = rentalCardNo.getNo().split("-")[0];

        assertThat(yearPrefix).isEqualTo(String.valueOf(LocalDate.now().getYear()));
    }
}
