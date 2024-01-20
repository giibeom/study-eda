package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.RentalItem;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode
public class ReturnedItem {

    private final RentalItem item;
    private final LocalDate returnDate;

    private ReturnedItem(RentalItem item, LocalDate returnDate) {
        this.item = item;
        this.returnDate = returnDate;
    }

    public static ReturnedItem create(RentalItem item) {
        return new ReturnedItem(item, LocalDate.now());
    }
}
