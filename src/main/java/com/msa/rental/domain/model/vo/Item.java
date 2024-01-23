package com.msa.rental.domain.model.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Item {

    private final int no;
    private final String title;

    @Builder
    public Item(int no, String title) {
        this.no = no;
        this.title = title;
    }
}
