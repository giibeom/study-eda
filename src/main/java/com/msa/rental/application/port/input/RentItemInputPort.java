package com.msa.rental.application.port.input;

import com.msa.rental.application.port.output.RentalCardOutputPort;
import com.msa.rental.application.usecase.RentItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RentItemInputPort implements RentItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Transactional
    public RentalCardOutputDTO rentItem(UserItemInputDTO rentalItemDto) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(rentalItemDto.getUserId())
                .orElseGet(() -> RentalCard.create(
                        new IDName(rentalItemDto.getUserId(), rentalItemDto.getUserNm())
                ));
        Item item = new Item(rentalItemDto.getItemId(), rentalItemDto.getItemTitle());

        rentalCard.rentItem(item);
        rentalCardOutputPort.save(rentalCard);

        return RentalCardOutputDTO.of(rentalCard);
    }
}
