package com.msa.rental.domain.model.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.Embeddable;

import static javax.persistence.AccessType.FIELD;
import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Access(FIELD)
@Embeddable
public class Item {

    private int no;
    private String title;

    @Builder
    public Item(int no, String title) {
        this.no = no;
        this.title = title;
    }
}
