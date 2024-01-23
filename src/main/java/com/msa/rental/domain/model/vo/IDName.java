package com.msa.rental.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class IDName {

    private final String id;
    private final String name;

    public IDName(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
