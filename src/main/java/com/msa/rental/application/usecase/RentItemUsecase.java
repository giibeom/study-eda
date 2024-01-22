package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;

public interface RentItemUsecase {
    RentalCardOutputDTO rentItem(UserItemInputDTO rentalItemDto);
}
