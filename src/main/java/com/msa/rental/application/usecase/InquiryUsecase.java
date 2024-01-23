package com.msa.rental.application.usecase;

import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;

import java.util.List;

public interface InquiryUsecase {

    RentalCardOutputDTO getRentalCard(String userId);

    List<RentItemOutputDTO> getAllRentItem(String userId);

    List<ReturnItemOutputDTO> getAllReturnItem(String userId);
}
