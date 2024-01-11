package com.msa.rental.domain.model.vo;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum RentStatus {
    RENT_AVAILABLE, // 대여 가능
    RENT_UNAVAILABLE // 대여 불가능
}
