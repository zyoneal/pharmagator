package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exceptions.PharmacyAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.PharmacyNotFoundException;
import com.eleks.academy.pharmagator.services.PharmacyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(pharmacyService.findAll());
        } catch (PharmacyNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pharmacyService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody PharmacyDto pharmacyDto) {
        try {
            return ResponseEntity.ok(pharmacyService.save(pharmacyDto));
        } catch (PharmacyAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/{id:[\\d]+}")
    public ResponseEntity<Pharmacy> update(
            @PathVariable Long id,
            @Valid @RequestBody PharmacyDto pharmacyDto) {

        return this.pharmacyService.update(id, pharmacyDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(pharmacyService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
