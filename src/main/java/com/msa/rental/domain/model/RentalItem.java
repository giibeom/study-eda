package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Item;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

public class RentalItem {

    private static final int DEFAULT_RENTAL_PERIOD = 14;

    @Getter
    private Item item;

    private LocalDate rentalDate;

    private LocalDate lateStartDate;

    @Getter
    private boolean overDued;

    @Getter
    private LocalDate overDueDate; // 반납 예정일

    @Builder(access = PRIVATE)
    private RentalItem(
            Item item, LocalDate rentalDate,
            LocalDate lateStartDate, boolean overDued, LocalDate overDueDate
    ) {
        this.item = item;
        this.rentalDate = rentalDate;
        this.lateStartDate = lateStartDate;
        this.overDued = overDued;
        this.overDueDate = overDueDate;
    }

    public static RentalItem create(Item item) {
        return RentalItem.builder()
                .item(item)
                .rentalDate(LocalDate.now())
                .overDued(false)
                .overDueDate(LocalDate.now().plusDays(DEFAULT_RENTAL_PERIOD))
                .build();
    }

    public void changeOverdueStatus(boolean isOverDue) {
        overDued = isOverDue;
    }

    public void changeOverDueDate(LocalDate overDueDate) {
        this.overDueDate = overDueDate;
    }
}
