package com.eleks.academy.pharmagator.controller;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.service.PharmacyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Controller
@RequestMapping("/pharmacies")
public class PharmacyController {

    @Autowired
    private PharmacyService ps;


    @GetMapping("/getListOfPharmacies")
    public ResponseEntity<List<Pharmacy>> getAllPharmacies() {
        return ResponseEntity.ok(ps.getAllPharmacies());
    }

    @GetMapping("/getPharmacyById/{pharmacyId}")
    public ResponseEntity<Pharmacy> getPharmacyById(@PathVariable("pharmacyId") Long pharmacyId) {
        return ResponseEntity.ok(ps.getPharmacy(pharmacyId));
    }

    @DeleteMapping("/deletePharmacyById/{pharmacyId}")
    public ResponseEntity<String> deletePharmacy(@PathVariable("pharmacyId") Long pharmacyId) {
        ps.deletePharmacy(pharmacyId);
        return ResponseEntity.ok("pharmacy id:" + pharmacyId + " was successfully deleted");
    }

    @PostMapping
    public ResponseEntity<Pharmacy> savePharmacy(@RequestBody Pharmacy pharmacy) {
        return new ResponseEntity<>(ps.saveOrUpdate(pharmacy), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Pharmacy> updatePharmacy(@RequestBody Pharmacy pharmacy) {
        return ResponseEntity.ok(ps.saveOrUpdate(pharmacy));
    }
}
