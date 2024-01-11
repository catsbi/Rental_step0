package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentItemOutputDTO {
    private Integer itemNo;
    private String itemTitle;

    private LocalDate rentDate;
    private boolean overdued;

    private LocalDate overdueDate;

    public static RentItemOutputDTO mapToDTO(RentalItem rentalItem) {
        RentItemOutputDTO dto = new RentItemOutputDTO();
        dto.setItemNo(rentalItem.getItem().getNo());
        dto.setItemTitle(rentalItem.getItem().getTitle());
        dto.setRentDate(rentalItem.getRentDate());
        dto.setOverdued(rentalItem.isOverduded());
        dto.setOverdueDate(rentalItem.getExpireDate());

        return dto;
    }
}
