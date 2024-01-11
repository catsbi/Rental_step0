package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.Item;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RentalItem {
    private Item item;
    private LocalDate rentDate;
    private boolean overduded;
    private LocalDate returnDate; // 반납 예정일

    public static RentalItem createRentalItem(Item item) {
        LocalDate now = LocalDate.now();
        return new RentalItem(
                item,
                now,
                false,
                now.plusDays(14)
        );
    }

    public static RentalItem sample() {
        return RentalItem.createRentalItem(Item.sample());
    }

    public static void main(String[] args) {
        System.out.println(sample());
    }
}
