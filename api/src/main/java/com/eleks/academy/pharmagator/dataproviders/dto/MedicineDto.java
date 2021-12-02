package com.eleks.academy.pharmagator.dataproviders.dto;

import com.univocity.parsers.annotations.Parsed;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {

    @Parsed(field = "title")
    private String title;

    @Parsed(field = "medicinePrice")
    private BigDecimal medicinePrice;

    @Parsed(field = "externalId")
    private String externalId;

    @Parsed(field = "pharmacyName")
    private String pharmacyName;

}
