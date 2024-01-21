package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.RentalStatus;
import com.msa.rental.domain.model.vo.ReturnedItem;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PRIVATE;

public class RentalCard {

    private static final int POINTS_PER_DELAYED_DAY = 10;

    private RentalCardNo rentalCardNo;

    private IDName member;

    @Getter
    private RentalStatus rentalStatus;

    @Getter
    private LateFee lateFee;

    private final List<RentalItem> rentalItems = new ArrayList<>();

    private final List<ReturnedItem> returnedItems = new ArrayList<>();

    @Builder(access = PRIVATE)
    private RentalCard(
            RentalCardNo rentalCardNo, IDName member,
            RentalStatus rentalStatus, LateFee lateFee
    ) {
        this.rentalCardNo = rentalCardNo;
        this.member = member;
        this.rentalStatus = rentalStatus;
        this.lateFee = lateFee;
    }

    public static RentalCard create(IDName creator) {
        return RentalCard.builder()
                .rentalCardNo(RentalCardNo.create())
                .member(creator)
                .rentalStatus(RentalStatus.RENTAL_ABLE)
                .lateFee(LateFee.create())
                .build();
    }

    public void rentItem(Item item) {
        validateRentalAvailable();
        addRentalItem(RentalItem.create(item));
    }

    private void validateRentalAvailable() {
        if (rentalStatus == RentalStatus.RENTAL_UNABLE) {
            throw new IllegalStateException("대여 불가 상태입니다.");
        }

        if (rentalItems.size() > 5) {
            throw new IllegalStateException("최대 대여 권수인 5권을 이미 대여중입니다.");
        }
    }

    private void addRentalItem(RentalItem item) {
        this.rentalItems.add(item);
    }

    public void returnItem(Item item, LocalDate returnDate) {
        RentalItem rentalItem = rentalItems.stream()
                .filter(it -> it.getItem().equals(item))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("반납하려는 도서는 현재 대여중인 도서가 아닙니다."));

        calculateLateFee(returnDate, rentalItem);
        removeRentalItem(rentalItem);
        addReturnedItem(ReturnedItem.create(rentalItem));
    }

    private void calculateLateFee(LocalDate returnDate, RentalItem rentalItem) {
        if (returnDate.isAfter(rentalItem.getOverDueDate())) {
            int delayedDays = Period.between(rentalItem.getOverDueDate(), returnDate).getDays();
            lateFee = lateFee.accumulate(delayedDays * POINTS_PER_DELAYED_DAY);
        }
    }

    private void removeRentalItem(RentalItem item) {
        this.rentalItems.remove(item);
    }

    private void addReturnedItem(ReturnedItem item) {
        this.returnedItems.add(item);
    }

    /**
     * 연체 포인트를 차감한 후, 차감하고 남은 포인트를 반환합니다.
     *
     * @param point 차감할 포인트
     * @return 차감하고 남은 포인트
     */
    public long deductLateFee(long point) {
        long pointsAfterDeduction = lateFee.getRemainingPointsAfterDeduction(point);

        lateFee = lateFee.deduct(point);
        if (lateFee.isEmpty()) {
            rentalStatus = RentalStatus.RENTAL_ABLE;
        }

        return pointsAfterDeduction;
    }

    // 원래는 배치 등을 활용하여 연체 여부를 계산해야되지만, 쉬운 예제를 위해 억지로 연체 처리 되는 메서드를 만듬
    public void overdueItem(Item item) {
        RentalItem rentalItem = rentalItems.stream()
                .filter(it -> it.getItem().equals(item))
                .findFirst().get();

        rentalItem.changeOverdueStatus(true);
        rentalStatus = RentalStatus.RENTAL_UNABLE;
        // 연체 억지로 만들기 - 실제로는 필요없는 코드
        rentalItem.changeOverDueDate(LocalDate.now().minusDays(1));
    }

    public boolean isExistRentalItem(Item item) {
        return rentalItems.stream().anyMatch(it -> it.getItem().equals(item));
    }
}
