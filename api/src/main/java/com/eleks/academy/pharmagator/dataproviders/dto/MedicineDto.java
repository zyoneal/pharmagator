package com.eleks.academy.pharmagator.dataproviders.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MedicineDto {

    private String title;

    private BigDecimal price;

    private String externalId;

    private Instant updatedAt;

}
