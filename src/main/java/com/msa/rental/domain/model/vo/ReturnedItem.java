package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.RentalItem;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDate;

import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class ReturnedItem {

    @Embedded
    private RentalItem returnItem;
    
    private LocalDate returnDate;

    private ReturnedItem(RentalItem returnItem, LocalDate returnDate) {
        this.returnItem = returnItem;
        this.returnDate = returnDate;
    }

    public static ReturnedItem create(RentalItem returnItem) {
        return new ReturnedItem(returnItem, LocalDate.now());
    }
}
