package com.eleks.academy.pharmagator.dataproviders.dto;

import com.univocity.parsers.annotations.Parsed;
<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
>>>>>>> 1813669471a97ed9089e76a1d3247bb0144d46f7
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicineDto {

<<<<<<< HEAD
    @Parsed(field = "name")
    private String title;

    @Parsed
    private BigDecimal price;

    @Parsed(field = "link")
=======
    @Parsed(field = "title")
    private String title;

    @Parsed(field = "price")
    private BigDecimal price;

    @Parsed(field = "externalId")
>>>>>>> 1813669471a97ed9089e76a1d3247bb0144d46f7
    private String externalId;

    @Parsed(field = "pharmacyName")
    private String pharmacyName;

}
