package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.exceptions.MedicineAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.MedicineNotFoundException;
import com.eleks.academy.pharmagator.services.MedicineService;
import com.eleks.academy.pharmagator.view.requests.MedicineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    @GetMapping
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(medicineService.findAll());
        } catch (MedicineNotFoundException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity getById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(medicineService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody MedicineRequest medicineRequest) {
        try {
            medicineService.save(medicineRequest);
            return ResponseEntity.ok("Medicine saved success");
        } catch (MedicineAlreadyExistException e) {
            return ResponseEntity.badRequest().body(e);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

    @PutMapping("/{id:[\\d]+}")
    public ResponseEntity<Medicine> update(
            @PathVariable Long id,
            @Valid @RequestBody MedicineRequest medicineRequest) {
        return this.medicineService.update(id, medicineRequest)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(medicineService.delete(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error");
        }
    }

}
