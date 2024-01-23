package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalCard;
import lombok.Builder;
import lombok.Getter;

import static lombok.AccessLevel.PRIVATE;

@Getter
public class RentalCardOutputDTO {
    private String rentalCardId;
    private String memberId;
    private String memberName;
    private String rentStatus;
    private long totalLateFee; // 전체 연체료
    private long totalRentalCnt; // 전체대여 도서건수
    private long totalReturnCnt; // 반납 도서건수
    private long totalOverduedCnt; // 연체중인 도서건수

    @Builder(access = PRIVATE)
    private RentalCardOutputDTO(
            String rentalCardId, String memberId, String memberName,
            String rentStatus, long totalLateFee, long totalRentalCnt,
            long totalReturnCnt, long totalOverduedCnt
    ) {
        this.rentalCardId = rentalCardId;
        this.memberId = memberId;
        this.memberName = memberName;
        this.rentStatus = rentStatus;
        this.totalLateFee = totalLateFee;
        this.totalRentalCnt = totalRentalCnt;
        this.totalReturnCnt = totalReturnCnt;
        this.totalOverduedCnt = totalOverduedCnt;
    }

    public static RentalCardOutputDTO of(RentalCard rental) {
        return RentalCardOutputDTO.builder()
                .rentalCardId(rental.getRentalCardNo().getNo())
                .memberId(rental.getMember().getId())
                .memberName(rental.getMember().getName())
                .rentStatus(rental.getRentalStatus().name())
                .totalLateFee(rental.getLateFee().getPoint())
                .totalRentalCnt(rental.getRentalItems().size())
                .totalReturnCnt(rental.getReturnedItems().size())
                .totalOverduedCnt(rental.getOverdueItemSize())
                .build();
    }
}
