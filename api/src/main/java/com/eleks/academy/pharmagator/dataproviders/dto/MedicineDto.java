package com.eleks.academy.pharmagator.dataproviders.dto;

<<<<<<< HEAD
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

=======
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MedicineDto {
    private String title;
    private BigDecimal price;
    private String externalId;
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
}
