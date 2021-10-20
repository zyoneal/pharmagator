package com.eleks.academy.pharmagator.dataproviders.dto.liki24;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Liki24MedicineDto {

    private Long productId;

    private String name;

    private String manufacturer;

    private BigDecimal price;

}
