package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalItem;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class RentItemOutputDTO {
    private Integer itemNo;
    private String itemtitle;
    private LocalDate rentDate;
    private boolean overdued; // 반납 예정일
    private LocalDate overdueDate;

    @Builder(access = PRIVATE)
    private RentItemOutputDTO(
            Integer itemNo, String itemtitle,
            LocalDate rentDate, boolean overdued, LocalDate overdueDate
    ) {
        this.itemNo = itemNo;
        this.itemtitle = itemtitle;
        this.rentDate = rentDate;
        this.overdued = overdued;
        this.overdueDate = overdueDate;
    }

    public static RentItemOutputDTO of(RentalItem rentItem) {
        return RentItemOutputDTO.builder()
                .itemNo(rentItem.getItem().getNo())
                .itemtitle(rentItem.getItem().getTitle())
                .rentDate(rentItem.getRentalDate())
                .overdued(rentItem.isOverDued())
                .overdueDate(rentItem.getOverDueDate())
                .build();
    }
}
