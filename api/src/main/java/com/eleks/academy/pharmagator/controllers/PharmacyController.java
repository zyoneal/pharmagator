package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.service.PharmacyService;
import com.eleks.academy.pharmagator.view.PharmacyRequest;
import com.eleks.academy.pharmagator.view.PharmacyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyService pharmacyService;

    @GetMapping
    public List<PharmacyResponse> getAllPharmacies() {
        return this.pharmacyService.getAllPharmacies()
                .stream()
                .map(PharmacyResponse::of)
                .collect(Collectors.toList());
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponse> getPharmacyById(@PathVariable("pharmacyId") Long pharmacyId) {
      return this.pharmacyService.getPharmacy(pharmacyId)
              .map(PharmacyResponse::of)
              .map(ResponseEntity::ok)
              .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{pharmacyId}")
    public ResponseEntity<String> deletePharmacy(@PathVariable("pharmacyId") Long pharmacyId) {
        this.pharmacyService.deletePharmacy(pharmacyId);
        return ResponseEntity.ok("pharmacy id:" + pharmacyId + " was successfully deleted");
    }

    @PostMapping
    public void createPharmacy(@RequestBody PharmacyRequest pharmacyRequest) {
        this.pharmacyService.createOrUpdate(pharmacyRequest);
    }

    @PutMapping
    public void updatePharmacy(@Valid @RequestBody PharmacyRequest pharmacyRequest) {
        this.pharmacyService.createOrUpdate(pharmacyRequest);
    }

}

