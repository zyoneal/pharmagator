package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PriceRepository;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.view.PriceRequest;
import com.eleks.academy.pharmagator.view.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PriceService {

    private PriceRepository priceRepository;

    public List<PriceResponse> getAllPrices() {
        return this.priceRepository.findAll().stream().map(PriceResponse::of).collect(Collectors.toList());
    }

    public void saveOrUpdate(PriceRequest priceRequest) {
        Price priceEntity = new Price();
        priceEntity.of(priceRequest);
        this.priceRepository.save(priceEntity);
    }

}
