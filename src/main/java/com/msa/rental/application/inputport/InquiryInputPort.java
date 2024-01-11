package com.msa.rental.application.inputport;

import com.msa.rental.application.outputport.RentalCardOutputPort;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class InquiryInputPort implements InquiryUsecase {
    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalCardOutputDTO getRentalCard(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(RentalCardOutputDTO::mapToDTO)
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
    }

    @Override
    public List<RentItemOutputDTO> getAllRentItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(loadCard -> loadCard.getRentItemList().stream()
                        .map(RentItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
    }

    @Override
    public List<ReturnItemOutputDTO> getAllReturnItem(UserInputDTO userInputDTO) {
        return rentalCardOutputPort.loadRentalCard(userInputDTO.getUserId())
                .map(loadCard -> loadCard.getReturnItemList().stream()
                        .map(ReturnItemOutputDTO::mapToDTO)
                        .collect(Collectors.toList()))
                .orElseThrow(() -> new IllegalArgumentException("해당 카드가 존재하지 않습니다."));
    }
}
