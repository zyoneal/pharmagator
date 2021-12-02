package com.eleks.academy.pharmagator.view;

import com.eleks.academy.pharmagator.entities.Price;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceResponse {

    private Long pharmacyId;

    private Long medicineId;

    private BigDecimal price;

    public static PriceResponse of(Price price) {
        return new PriceResponse(price.getPharmacyId(), price.getMedicineId(), price.getPrice());
    }

}
