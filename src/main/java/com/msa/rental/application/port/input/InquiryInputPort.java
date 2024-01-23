package com.msa.rental.application.port.input;

import com.msa.rental.application.port.output.RentalCardOutputPort;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.domain.model.RentalCard;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class InquiryInputPort implements InquiryUsecase {

    private final RentalCardOutputPort rentalCardOutputPort;

    public RentalCardOutputDTO getRentalCard(UserInputDTO userInputDTO) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(userInputDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("대여카드가 존재하지 않습니다."));

        return RentalCardOutputDTO.of(rentalCard);
    }

    public List<RentItemOutputDTO> getAllRentItem(UserInputDTO userInputDTO) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(userInputDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("대여카드가 존재하지 않습니다."));

        return rentalCard.getRentalItems().stream()
                .map(RentItemOutputDTO::of)
                .collect(Collectors.toList());
    }

    public List<ReturnItemOutputDTO> getAllReturnItem(UserInputDTO userInputDTO) {
        RentalCard rentalCard = rentalCardOutputPort.findByUserId(userInputDTO.getUserId())
                .orElseThrow(() -> new IllegalStateException("대여카드가 존재하지 않습니다."));

        return rentalCard.getReturnedItems().stream()
                .map(ReturnItemOutputDTO::of)
                .collect(Collectors.toList());
    }
}
