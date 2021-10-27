package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.service.PriceService;
import com.eleks.academy.pharmagator.view.PriceRequest;
import com.eleks.academy.pharmagator.view.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public List<PriceResponse> getAllPrices() {
        return this.priceService.getAllPrices().stream().map(PriceResponse::of).collect(Collectors.toList());
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
