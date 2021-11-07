package com.eleks.academy.pharmagator.dataproviders.dto.input;

import com.eleks.academy.pharmagator.entities.Price;
import lombok.Data;

import javax.validation.constraints.Min;
import java.math.BigDecimal;

@Data
public class PriceDto {

    @Min(value = 0)
    private BigDecimal price;

    private String externalId;

    public static PriceDto toDto(Price price) {
        PriceDto priceDto = new PriceDto();
        priceDto.setPrice(price.getPrice());
        priceDto.setExternalId(price.getExternalId());
        return priceDto;
    }

    public static Price toEntity(PriceDto priceDto) {
        Price price = new Price();
        price.setPrice(priceDto.getPrice());
        price.setExternalId(priceDto.getExternalId());
        return price;
    }

}
