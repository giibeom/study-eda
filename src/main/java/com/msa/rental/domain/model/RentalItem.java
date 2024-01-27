package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Item;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

import static javax.persistence.AccessType.FIELD;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@NoArgsConstructor(access = PROTECTED)
@Access(FIELD)
@Embeddable
public class RentalItem {

    private static final int DEFAULT_RENTAL_PERIOD = 14;

    @Getter
    @Embedded
    private Item item;

    @Getter
    private LocalDate rentalDate;

    @Getter
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

    public static RentalItem copy(RentalItem original) {
        return RentalItem.builder()
                .item(original.getItem())
                .rentalDate(original.getRentalDate())
                .lateStartDate(original.getLateStartDate())
                .overDued(original.isOverDued())
                .overDueDate(original.getOverDueDate())
                .build();
    }

    public void changeOverdueStatus(boolean isOverDue) {
        overDued = isOverDue;
    }

    public void changeOverDueDate(LocalDate overDueDate) {
        this.overDueDate = overDueDate;
    }
}
