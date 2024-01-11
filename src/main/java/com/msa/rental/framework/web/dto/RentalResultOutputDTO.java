package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalCard;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalResultOutputDTO {
    private String userId;
    private String userNm;
    private Integer rentedCount;
    private long totalLateFee;

    public static RentalResultOutputDTO mapToDTO(RentalCard rental) {
        RentalResultOutputDTO dto = new RentalResultOutputDTO();
        dto.setUserId(rental.getMember().getId());
        dto.setUserNm(rental.getMember().getName());
        dto.setRentedCount(rental.getRentItemList().size());
        dto.setTotalLateFee(rental.getLateFee().getPoint());

        return dto;
    }
}
