package com.msa.rental.domain.model.vo;

import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalCardNo {
    private String no;

    public static RentalCardNo createRentalCardNo() {
        UUID uuid = UUID.randomUUID();
        String year = LocalDate.now().getYear() + "";

        return new RentalCardNo(year + "-" + uuid);
    }

    public static RentalCardNo sample() {
        return RentalCardNo.createRentalCardNo();
    }
}
