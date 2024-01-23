package com.msa.rental.application.port.output;

import com.msa.rental.domain.model.RentalCard;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RentalCardOutputPort {

    Optional<RentalCard> findByUserId(String userId);

    RentalCard save(RentalCard rentalCard);
}
