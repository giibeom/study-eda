package com.msa.rental.framework.web.dto;

import lombok.Getter;

@Getter
public class ClearOverdueInfoDTO {
    private String userId;
    private String userNm;
    private int point;
}
