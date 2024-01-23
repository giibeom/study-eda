package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.vo.ReturnedItem;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class ReturnItemOutputDTO {
    private final int itemNo;
    private final String itemTitle;
    private final LocalDate returnDate;

    @Builder(access = PRIVATE)
    private ReturnItemOutputDTO(int itemNo, String itemTitle, LocalDate returnDate) {
        this.itemNo = itemNo;
        this.itemTitle = itemTitle;
        this.returnDate = returnDate;
    }

    public static ReturnItemOutputDTO of(ReturnedItem returnedItem) {
        return ReturnItemOutputDTO.builder()
                .itemNo(returnedItem.getReturnItem().getItem().getNo())
                .itemTitle(returnedItem.getReturnItem().getItem().getTitle())
                .returnDate(returnedItem.getReturnDate())
                .build();
    }
}
