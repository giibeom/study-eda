package com.msa.rental.framework.web;

import com.msa.rental.application.usecase.ClearOverdueItemUsecase;
import com.msa.rental.application.usecase.CreateRentalCardUsecase;
import com.msa.rental.application.usecase.InquiryUsecase;
import com.msa.rental.application.usecase.OverdueItemUsecase;
import com.msa.rental.application.usecase.RentItemUsecase;
import com.msa.rental.application.usecase.ReturnItemUsecase;
import com.msa.rental.framework.web.dto.ClearOverdueInfoDTO;
import com.msa.rental.framework.web.dto.RentItemOutputDTO;
import com.msa.rental.framework.web.dto.RentalCardOutputDTO;
import com.msa.rental.framework.web.dto.RentalResultOutputDTO;
import com.msa.rental.framework.web.dto.ReturnItemOutputDTO;
import com.msa.rental.framework.web.dto.UserInputDTO;
import com.msa.rental.framework.web.dto.UserItemInputDTO;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rental")
public class RentalController {

    private final RentItemUsecase rentItemUsecase;
    private final ReturnItemUsecase returnItemUsecase;
    private final CreateRentalCardUsecase createRentalCardUsecase;
    private final OverdueItemUsecase overdueItemUsecase;
    private final ClearOverdueItemUsecase clearOverdueItemUsecase;
    private final InquiryUsecase inquiryUsecase;

    @ApiOperation(value = "도서카드 생성", notes = "사용자정보 -> 도서카드정보")
    @PostMapping("/card")
    @ResponseStatus(HttpStatus.CREATED)
    RentalCardOutputDTO createRentalCard(@RequestBody UserInputDTO userInputDTO) {
        return createRentalCardUsecase.createRentalCard(userInputDTO);
    }

    @ApiOperation(value = "도서카드 조회", notes = "사용자정보(id) -> 도서카드정보")
    @GetMapping("/card/{userId}")
    RentalCardOutputDTO getRentalCard(@PathVariable String userId) {
        return inquiryUsecase.getRentalCard(userId);
    }

    @ApiOperation(value = "대여도서목록 조회", notes = "사용자정보(id) -> 대여도서목록 조회")
    @GetMapping("/rent-books/{userId}")
    List<RentItemOutputDTO> getAllRentItem(@PathVariable String userId) {
        return inquiryUsecase.getAllRentItem(userId);
    }

    @ApiOperation(value = "반납도서목록 조회", notes = "사용자정보(id) -> 반납도서목록 조회")
    @GetMapping("/return-books/{userId}")
    List<ReturnItemOutputDTO> getAllReturnItem(@PathVariable String userId) {
        return inquiryUsecase.getAllReturnItem(userId);
    }

    @ApiOperation(value = "도서 대여", notes = "사용자정보, 아이템정보 -> 도서카드정보")
    @PostMapping("/rent-book")
    RentalCardOutputDTO rentItem(@RequestBody UserItemInputDTO userItemInputDTO) {
        return rentItemUsecase.rentItem(userItemInputDTO);
    }

    @ApiOperation(value = "도서 반납", notes = "사용자정보, 아이템정보 -> 도서카드정보")
    @PostMapping("/return-book")
    RentalCardOutputDTO returnItem(@RequestBody UserItemInputDTO userItemInputDTO) {
        return returnItemUsecase.returnItem(userItemInputDTO);
    }

    @ApiOperation(value = "연체 처리", notes = "사용자정보, 아이템정보 -> 도서카드정보")
    @PostMapping("/overdue")
    RentalCardOutputDTO overdueItem(@RequestBody UserItemInputDTO userItemInputDTO) {
        return overdueItemUsecase.overDueItem(userItemInputDTO);
    }


    @ApiOperation(value = "연체 해제", notes = "사용자정보, 포인트 -> 도서카드정보")
    @PostMapping("/clear-overdue")
    RentalResultOutputDTO clearOverdueItem(@RequestBody ClearOverdueInfoDTO clearOverdueInfoDTO) {
        return clearOverdueItemUsecase.clearOverdue(clearOverdueInfoDTO);
    }
}

