package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.RentalItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@EqualsAndHashCode
public class ReturnedItem {

    private final RentalItem returnItem;
    private final LocalDate returnDate;

    private ReturnedItem(RentalItem returnItem, LocalDate returnDate) {
        this.returnItem = returnItem;
        this.returnDate = returnDate;
    }

    public static ReturnedItem create(RentalItem returnItem) {
        return new ReturnedItem(returnItem, LocalDate.now());
    }
}
