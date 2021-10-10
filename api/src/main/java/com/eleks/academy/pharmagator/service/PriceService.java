package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PriceRepository;
import com.eleks.academy.pharmagator.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;


    public List<Price> getAllPrices() {
        return priceRepository.findAll();
    }

    //TODO get price
/*    public Price getPrice() {
        return priceRepository.findById(1L).get();
    }*/

    //TODO delete price
/*    public void deletePrice(Long medicineId) {
        priceRepository.deleteById(medicineId);
    }*/

    public Price saveOrUpdate(Price price) {
        return priceRepository.save(price);
    }
}
