package com.eleks.academy.pharmagator.controller;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/prices")
public class PriceController {
    @Autowired
    private PriceService ps;

    @GetMapping("/getListOfPrices")
    public ResponseEntity<List<Price>> getAllPrices() {
        return ResponseEntity.ok(ps.getAllPrices());
    }


    //TODO get price by id
/*    @GetMapping("/getPriceById")
    public ResponseEntity<Price> getPrice() {
        return ResponseEntity.ok(ps.getPrice());
    }*/

    //TODO delete price by id
/*    @DeleteMapping("/deletePriceById/{medicineId}")
    public void deletePrice(@PathVariable("medicineId") Long medicineId) {
        ps.deletePrice(medicineId);
    }*/


    @PostMapping
    public ResponseEntity<Price> savePrice(@RequestBody Price price) {
        return new ResponseEntity<>(ps.saveOrUpdate(price), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Price> updatePharmacy(@RequestBody Price price) {
        return ResponseEntity.ok(ps.saveOrUpdate(price));
    }
}
