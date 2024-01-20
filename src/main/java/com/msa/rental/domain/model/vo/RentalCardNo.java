package com.msa.rental.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@EqualsAndHashCode
public class RentalCardNo {

    private final String no;

    public RentalCardNo(String no) {
        this.no = no;
    }
    
    public static RentalCardNo create() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());
        
        return new RentalCardNo(year + "-" + uuid);
    }
}
