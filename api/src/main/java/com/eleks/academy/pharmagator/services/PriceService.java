package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.view.requests.PriceRequest;

import java.util.List;
import java.util.Optional;

public interface PriceService {

    List<Price> findAll();

    Optional<Price> findById(Long pharmacyId, Long medicineId);

    Price save(PriceRequest priceDto);

    Optional<Price> update(Long pharmacyId, Long medicineId, PriceRequest priceDto);

    void deleteById(Long pharmacyId, Long medicineId);
    
}
