package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.vo.ReturnItem;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReturnItemOutputDTO {
    private Integer itemNo;
    private String itemTitle;
    private LocalDate returnDate;

    public static ReturnItemOutputDTO mapToDTO(ReturnItem returnItem) {
        ReturnItemOutputDTO dto = new ReturnItemOutputDTO();
        dto.setItemNo(returnItem.getRentalItem().getItem().getNo());
        dto.setItemTitle(returnItem.getRentalItem().getItem().getTitle());
        dto.setReturnDate(returnItem.getReturnDate());

        return dto;
    }
}
