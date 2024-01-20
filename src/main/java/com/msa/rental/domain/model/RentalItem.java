package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Item;
import lombok.Builder;

import java.time.LocalDate;

public class RentalItem {

    private static final int DEFAULT_RENTAL_PERIOD = 14;
    private Item item;
    private LocalDate rentalDate;
    private LocalDate lateStartDate;
    private boolean overDued;
    private LocalDate overDueDate; // 반납 예정일

    @Builder
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
}
