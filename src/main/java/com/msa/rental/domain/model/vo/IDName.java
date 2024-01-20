package com.msa.rental.domain.model.vo;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class IDName {

    private final String id;
    private final String name;

    @Builder
    private IDName(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
