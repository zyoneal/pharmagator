package com.eleks.academy.pharmagator.dataproviders.converters;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.view.requests.PriceRequest;

public class PriceConverter {

    public static Price of(PriceRequest priceRequest) {
        Price price = new Price();
        price.setPrice(priceRequest.getPrice());
        return price;
    }

}
