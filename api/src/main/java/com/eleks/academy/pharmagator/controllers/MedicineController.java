package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.exceptions.InvalidIdentifierException;
import com.eleks.academy.pharmagator.services.MedicineService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;
    private final ModelMapper modelMapper;

    @GetMapping
    public List<MedicineDto> getAll() {
        return medicineService.findAll().stream()
                .map(medicine -> modelMapper.map(medicine, MedicineDto.class))
                .toList();
    }

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<MedicineDto> getById(@PathVariable Long id) {
        return medicineService.findById(id)
                .map(medicine -> ResponseEntity.ok(modelMapper.map(medicine, MedicineDto.class)))
                .orElseThrow(() -> new InvalidIdentifierException(id));
    }

    @PostMapping
    public MedicineDto create(@Valid @RequestBody MedicineDto medicineDto) {
        return modelMapper.map(medicineService.save(medicineDto), MedicineDto.class);
    }

    @PutMapping("/{id:[\\d]+}")
    public ResponseEntity<MedicineDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MedicineDto medicineDto) {

        return medicineService.update(id, medicineDto)
                .map(medicine -> ResponseEntity.ok(modelMapper.map(medicine, MedicineDto.class)))
                .orElseThrow(() -> new InvalidIdentifierException(id));
    }

    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity<MedicineDto> delete(@PathVariable Long id) {
        medicineService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}