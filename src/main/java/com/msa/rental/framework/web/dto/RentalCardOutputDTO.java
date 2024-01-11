package com.msa.rental.framework.web.dto;

import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.RentalItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RentalCardOutputDTO {
    private String rentalCardId;
    private String memberId;
    private String memberName;

    // 대여 가능 여부
    private String rentStatus;

    // 전체 연체료
    private Long totalLateFee;

    //전체 대여 건수
    private Long totalRentalCnt;

    // 전체 반납 도서 권수
    private Long totalReturnCnt;

    // 연체중인 도서 건수
    private Long totalOverdudedCnt;

    public static RentalCardOutputDTO mapToDTO(RentalCard rental){
        RentalCardOutputDTO rentDTO = new RentalCardOutputDTO();
        rentDTO.setRentalCardId(rental.getRentalCardNo().getNo());
        rentDTO.setMemberId(rental.getMember().getId());
        rentDTO.setMemberName(rental.getMember().getName());
        rentDTO.setRentStatus(rental.getRentStatus().toString());
        rentDTO.setTotalRentalCnt((long) rental.getRentalItemList().size());
        rentDTO.setTotalReturnCnt((long) rental.getReturnItemList().size());
        rentDTO.setTotalOverdudedCnt(rental.getRentalItemList().stream().filter(RentalItem::isOverduded).count());
        return rentDTO;
    }

}
