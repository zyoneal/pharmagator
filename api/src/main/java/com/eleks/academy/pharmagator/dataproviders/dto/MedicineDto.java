package com.eleks.academy.pharmagator.dataproviders.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.Instant;

@Data
public class MedicineDto {
    private String title;
    private BigDecimal price;
    private String externalId;
    private Instant updatedAt;
}
