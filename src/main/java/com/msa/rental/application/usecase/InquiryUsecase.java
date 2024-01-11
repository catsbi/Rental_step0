package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import java.util.List;

public interface InquiryUsecase {
    public RentalCardOutputDTO getRentalCard(UserInputDTO userInputDTO);

    public List<RentItemOutputDTO> getAllRentItem(UserInputDTO userInputDTO);

    public List<ReturnItemOutputDTO> getAllReturnItem(UserInputDTO userInputDTO);


}
