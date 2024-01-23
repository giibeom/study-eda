package com.msa.rental.application.port.input;

import com.msa.rental.application.port.output.RentalCardOutputPort;
import com.msa.rental.application.usecase.ClearOverdueItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClearOverdueItemInputPort implements ClearOverdueItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Override
    public RentalResultOutputDTO clearOverdue(ClearOverdueInfoDTO clearOverdueInfoDto) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(clearOverdueInfoDto.getUserId())
                .orElseThrow(() -> new IllegalStateException("대여 카드가 존재하지 않습니다."));

        rentalCard.makeAvailableRental(clearOverdueInfoDto.getPoint());

        return RentalResultOutputDTO.of(rentalCard);
    }
}
