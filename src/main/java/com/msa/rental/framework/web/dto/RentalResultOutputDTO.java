package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalCard;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class RentalResultOutputDTO {
    private String userId;
    private String userNm;
    private long rentedCount;
    private long totalLateFee;

    @Builder(access = PRIVATE)
    private RentalResultOutputDTO(String userId, String userNm, long rentedCount, long totalLateFee) {
        this.userId = userId;
        this.userNm = userNm;
        this.rentedCount = rentedCount;
        this.totalLateFee = totalLateFee;
    }

    public static RentalResultOutputDTO of(RentalCard rentalCard) {
        return RentalResultOutputDTO.builder()
                .userId(rentalCard.getMember().getId())
                .userNm(rentalCard.getMember().getName())
                .rentedCount(rentalCard.getRentalItemSize())
                .totalLateFee(rentalCard.getLateFee().getPoint())
                .build();
    }
}
