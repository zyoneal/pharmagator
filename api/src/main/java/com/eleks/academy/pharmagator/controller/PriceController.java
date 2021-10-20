package com.eleks.academy.pharmagator.controller;

import com.eleks.academy.pharmagator.service.PriceService;
import com.eleks.academy.pharmagator.view.PriceRequest;
import com.eleks.academy.pharmagator.view.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public List<PriceResponse> getAllPrices() {
        return this.priceService.getAllPrices();
    }

    @PostMapping
    public void createPrice(@RequestBody PriceRequest priceRequest) {
        this.priceService.saveOrUpdate(priceRequest);
    }

    @PutMapping
    public void updatePrice(@RequestBody PriceRequest priceRequest) {
        this.priceService.saveOrUpdate(priceRequest);
    }

}
