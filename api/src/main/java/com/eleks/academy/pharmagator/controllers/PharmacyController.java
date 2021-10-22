package com.eleks.academy.pharmagator.controllers;

<<<<<<< HEAD
import com.eleks.academy.pharmagator.service.PharmacyService;
import com.eleks.academy.pharmagator.view.PharmacyResponse;
import com.eleks.academy.pharmagator.view.PharmacyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
=======
import com.eleks.academy.pharmagator.projections.PharmacyLight;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
>>>>>>> development

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

<<<<<<< HEAD
    private final PharmacyService pharmacyService;

    @GetMapping
    public List<PharmacyResponse> getAllPharmacies() {
        return this.pharmacyService.getAllPharmacies();
    }

    @GetMapping("/{pharmacyId}")
    public ResponseEntity<PharmacyResponse> getPharmacyById(@PathVariable("pharmacyId") Long pharmacyId) {
        return this.pharmacyService.getPharmacy(pharmacyId);
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

=======
    private final PharmacyRepository pharmacyRepository;

    @GetMapping
    public List<PharmacyLight> getAll() {
        return this.pharmacyRepository.findAllLight();
    }

>>>>>>> development
}

