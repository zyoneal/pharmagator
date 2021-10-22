package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.service.MedicineService;
import com.eleks.academy.pharmagator.view.MedicineRequest;
import com.eleks.academy.pharmagator.view.MedicineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public List<MedicineResponse> getAllMedicines() {
        return this.medicineService.getAllMedicines();
    }

    @GetMapping("/{medicineId}")
    public ResponseEntity<MedicineResponse> getMedicineById(@PathVariable("medicineId") Long medicineId) {
        return this.medicineService.getMedicine(medicineId);
    }

    @DeleteMapping("/{medicineId}")
    public ResponseEntity<String> deleteMedicine(@PathVariable("medicineId") Long medicineId) {
        this.medicineService.deleteMedicine(medicineId);
        return ResponseEntity.ok("Medicine id:" + medicineId + " was successfully deleted");
    }

    @PostMapping
    public void createMedicine(@RequestBody MedicineRequest medicineRequest) {
        this.medicineService.createOrUpdate(medicineRequest);
    }

    @PutMapping
    public void updateMedicine(@RequestBody MedicineRequest medicineRequest) {
        this.medicineService.createOrUpdate(medicineRequest);
    }

}

