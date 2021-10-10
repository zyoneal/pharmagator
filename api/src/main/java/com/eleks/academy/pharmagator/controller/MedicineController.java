package com.eleks.academy.pharmagator.controller;


import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/medicines")
public class MedicineController {

    @Autowired
    private MedicineService ms;

    @GetMapping("/getListOfMedicines")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        return ResponseEntity.ok(ms.getAllMedicines());
    }

    @GetMapping("/getMedicineById/{medicineId}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable("medicineId") Long medicineId) {
        return ResponseEntity.ok(ms.getMedicine(medicineId));
    }

    @DeleteMapping("/deleteMedicineById/{medicineId}")
    public ResponseEntity<String> deleteMedicine(@PathVariable("medicineId") Long medicineId) {
        ms.deleteMedicine(medicineId);
        return ResponseEntity.ok("Medicine id:" + medicineId + " was successfully deleted");
    }


    @PostMapping
    public ResponseEntity<Medicine> saveMedicine(@RequestBody Medicine medicine) {
        return new ResponseEntity<>(ms.saveOrUpdate(medicine), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Medicine> updateMedicine(@RequestBody Medicine medicine) {
        return ResponseEntity.ok(ms.saveOrUpdate(medicine));
    }
}
