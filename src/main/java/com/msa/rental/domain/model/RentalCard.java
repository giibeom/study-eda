package com.msa.rental.domain.model;

import com.msa.rental.domain.model.vo.IDName;
import com.msa.rental.domain.model.vo.LateFee;
import com.msa.rental.domain.model.vo.RentalCardNo;
import com.msa.rental.domain.model.vo.RentalStatus;
import com.msa.rental.domain.model.vo.ReturnedItem;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class RentalCard {

    private RentalCardNo no;
    private IDName member;
    private RentalStatus rentalStatus;
    private LateFee lateFee;
    private final List<RentalItem> rentalItems = new ArrayList<>();
    private final List<ReturnedItem> returnedItems = new ArrayList<>();

    @Builder
    private RentalCard(
            RentalCardNo no, IDName member,
            RentalStatus rentalStatus, LateFee lateFee
    ) {
        this.no = no;
        this.member = member;
        this.rentalStatus = rentalStatus;
        this.lateFee = lateFee;
    }

    public void addRentalItem(RentalItem item) {
        this.rentalItems.add(item);
    }

    public void removeRentalItem(RentalItem item) {
        this.rentalItems.remove(item);
    }

    public void addReturnedItem(ReturnedItem item) {
        this.returnedItems.add(item);
    }

    public void removeReturnedItem(ReturnedItem item) {
        this.returnedItems.remove(item);
    }
}
