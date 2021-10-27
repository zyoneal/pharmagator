package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PriceRepository;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.view.PriceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PriceService {

    private PriceRepository priceRepository;

    public List<Price> getAllPrices() {
        return this.priceRepository.findAll();
    }

    public void saveOrUpdate(PriceRequest priceRequest) {
        Price priceEntity = new Price();
        Price priceToSaveOrUpdate = priceEntity.of(priceRequest);
        this.priceRepository.save(priceToSaveOrUpdate);
    }

}
