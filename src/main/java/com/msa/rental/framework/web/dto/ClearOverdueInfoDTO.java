package com.msa.rental.framework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClearOverdueInfoDTO {
    private String userId;
    private String userNm;
    private Integer point;
}
