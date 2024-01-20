package com.msa.rental.domain.model.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Item {

    private final int no;
    private final String title;

    @Builder
    private Item(int no, String title) {
        this.no = no;
        this.title = title;
    }
}
