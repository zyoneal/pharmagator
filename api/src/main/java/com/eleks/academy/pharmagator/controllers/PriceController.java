package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.services.PriceService;
import com.eleks.academy.pharmagator.view.requests.PriceRequest;
import com.eleks.academy.pharmagator.view.responses.PriceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping
    public List<Price> getAll() {
        return this.priceService.findAll();
    }

    @GetMapping("/pharmacyId/{pharmacyId:[\\d]+}/medicineId/{medicineId:[\\d]+}")
    public ResponseEntity<Price> getById(
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId) {

        return this.priceService.findById(pharmacyId, medicineId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Price create(@Valid @RequestBody PriceRequest priceRequest) {
        return this.priceService.save(priceRequest);
    }

    @PostMapping("/pharmacyId/{pharmacyId:[\\d]+}/medicineId/{medicineId:[\\d]+}")
    public ResponseEntity<Price> update(
            @Valid @RequestBody PriceRequest priceRequest,
            @PathVariable Long pharmacyId,
            @PathVariable Long medicineId) {

        return this.priceService.update(pharmacyId, medicineId, priceRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/pharmacyId/{pharmacyId:[\\d]+}/medicineId/{medicineId:[\\d]+}")
    public ResponseEntity<?> delete(@PathVariable Long pharmacyId, @PathVariable Long medicineId) {
        this.priceService.deleteById(pharmacyId, medicineId);
        return ResponseEntity.noContent().build();
    }

}
