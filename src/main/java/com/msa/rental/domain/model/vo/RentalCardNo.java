package com.msa.rental.domain.model.vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class RentalCardNo implements Serializable {

    @Id
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
