package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;

public interface CreateRentalCardUsecase {
    RentalCardOutputDTO createRental(UserInputDTO userInputDTO);
}
