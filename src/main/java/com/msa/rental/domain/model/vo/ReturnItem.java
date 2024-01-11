package com.msa.rental.domain.model.vo;

import com.msa.rental.domain.model.RentalItem;
import java.time.LocalDate;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ReturnItem {
    @Embedded
    private RentalItem rentalItem;
    private LocalDate returnDate;

    public static ReturnItem createReturnItem(RentalItem rentalItem) {
        return new ReturnItem(rentalItem, LocalDate.now());
    }

    public static ReturnItem sample() {
        return ReturnItem.createReturnItem(RentalItem.sample());
    }

    public static void main(String[] args) {
        System.out.println(ReturnItem.sample());
    }
}
