package com.msa.rental.domain.model.vo;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

import static lombok.AccessLevel.PROTECTED;

@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class IDName {

    private String id;
    private String name;

    public IDName(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
