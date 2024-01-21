package com.msa.rental.integration;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/*
    테스트 시나리오 구상해 보기
    - 도서샘플 1,2 생성
    - 도서 1 ,2 대여
    - 도서 1 반납
    - 도서2 강제연체 처리 -> 대여정지됨
    - 도서2 반납 -> 연체료 계산됨
    - 정지해제처리 -> 포인트로 연체료 삭감
*/
public class RentTest {

    @Nested
    @DisplayNameGeneration(ReplaceUnderscores.class)
    class 도서_대여_반납_연체_정지해제_시나리오 {

        @Nested
        @DisplayNameGeneration(ReplaceUnderscores.class)
        class 대여카드와_도서샘플_2개가_주어지고_주어진_도서들을_대여할_때 {
            RentalCard 기범_카드 = RentalCard.create(IDName.builder().id("1").name("기범").build());
            Item 도서1 = Item.builder().no(1).title("도서1").build();
            Item 도서2 = Item.builder().no(2).title("도서2").build();

            @BeforeEach
            void setUp() {
                기범_카드.rentItem(도서1);
                기범_카드.rentItem(도서2);
            }

            @Test
            @DisplayName("도서 1을 반납하면 도서 1은 대여 품목에 존재하지 않는다.")
            void it_returns_false() {
                기범_카드.returnItem(도서1, LocalDate.now());
                assertThat(기범_카드.isExistRentalItem(도서1)).isFalse();
            }

            @Test
            @DisplayName("도서 1을 반납하면 도서 2는 대여 품목에 존재한다.")
            void it_returns_true() {
                기범_카드.returnItem(도서1, LocalDate.now());
                assertThat(기범_카드.isExistRentalItem(도서2)).isTrue();
            }

            @Test
            @DisplayName("도서 2가 연체되면 대여 불가능 상태로 변경되어 도서를 대여할 경우 예외가 발생한다.")
            void it_returns_exception() {
                기범_카드.overdueItem(도서2);
                Item 도서3 = Item.builder().no(3).title("도서3").build();

                assertThat(기범_카드.getRentalStatus()).isEqualTo(RentalStatus.RENTAL_UNABLE);
                assertThatThrownBy(() -> 기범_카드.rentItem(도서3))
                        .isInstanceOf(IllegalStateException.class);
            }

            @Test
            @DisplayName("하루 연체된 도서2를 반납할 경우 연체 포인트는 10포인트가 누적된다.")
            void it_added_10_late_fee_point() {
                기범_카드.overdueItem(도서2);
                기범_카드.returnItem(도서2, LocalDate.now());

                assertThat(기범_카드.getLateFee()).isEqualTo(LateFee.create(10));
            }

            @Test
            @DisplayName("하루 연체된 도서 2를 반납하여 생긴 연체 포인트 10포인트를 모두 차감할 경우 대여 가능 상태로 변경된다.")
            void it_changed_rental_able() {
                기범_카드.overdueItem(도서2);
                기범_카드.returnItem(도서2, LocalDate.now());

                기범_카드.deductLateFee(10L);

                assertThat(기범_카드.getRentalStatus()).isEqualTo(RentalStatus.RENTAL_ABLE);
            }
        }
    }
}
