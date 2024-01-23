package com.msa.rental.application.port.input;

import com.msa.rental.application.port.output.RentalCardOutputPort;
import com.msa.rental.application.usecase.CreateRentalCardUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CreateRentalCardInputPort implements CreateRentalCardUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Transactional
    public RentalCardOutputDTO createRentalCard(UserInputDTO owner) {
        RentalCard rentalCard = RentalCard.create(new IDName(owner.getUserId(), owner.getUserNm()));
        rentalCardOutputPort.save(rentalCard);

        return RentalCardOutputDTO.of(rentalCard);
    }
}
