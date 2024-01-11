package com.msa.rental.framework.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserItemInputDTO {
    private String userId;
    private String userNm;
    private Integer itemId;
    private String itemTitle;
}
