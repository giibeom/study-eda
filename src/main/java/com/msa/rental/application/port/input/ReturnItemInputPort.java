package com.msa.rental.application.port.input;

import com.msa.rental.application.port.output.RentalCardOutputPort;
import com.msa.rental.application.usecase.ReturnItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReturnItemInputPort implements ReturnItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    @Transactional
    public RentalCardOutputDTO returnItem(UserItemInputDTO returnItemDto) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(returnItemDto.getUserId())
                .orElseThrow(() -> new IllegalStateException("해당 카드가 존재하지 않습니다."));
        Item item = new Item(returnItemDto.getItemId(), returnItemDto.getItemTitle());

        rentalCard.returnItem(item, LocalDate.now());

        return RentalCardOutputDTO.of(rentalCard);
    }
}


