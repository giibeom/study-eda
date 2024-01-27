package com.msa.rental.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class RentalCardNo implements Serializable {

    private String no;

    public RentalCardNo(String no) {
        this.no = no;
    }

    public static RentalCardNo create() {
        UUID uuid = UUID.randomUUID();
        String year = String.valueOf(LocalDate.now().getYear());

        return new RentalCardNo(year + "-" + uuid);
    }
}
