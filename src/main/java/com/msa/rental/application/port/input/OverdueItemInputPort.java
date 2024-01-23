package com.msa.rental.application.port.input;

import com.msa.rental.application.port.output.RentalCardOutputPort;
import com.msa.rental.application.usecase.OverdueItemUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.domain.model.vo.Item;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OverdueItemInputPort implements OverdueItemUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;
    
    @Transactional
    public RentalCardOutputDTO overDueItem(UserItemInputDTO rentalItemDto) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(rentalItemDto.getUserId())
                .orElseThrow(() -> new IllegalStateException("해당 카드가 존재하지 않습니다."));
        Item item = new Item(rentalItemDto.getItemId(), rentalItemDto.getItemTitle());

        rentalCard.overdueItem(item);

        return RentalCardOutputDTO.of(rentalCard);
    }
}
