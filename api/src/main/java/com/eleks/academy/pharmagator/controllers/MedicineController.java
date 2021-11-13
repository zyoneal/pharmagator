package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.services.MedicineService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/medicines")
@RequiredArgsConstructor
public class MedicineController {

    private final MedicineService medicineService;

    private final ModelMapper modelMapper;

    @GetMapping ResponseEntity<Page<MedicineDto>> getAll(@RequestParam(value = "page", required = false,defaultValue = "0") int page,
                                                         @RequestParam(value = "size",required = false,defaultValue = "10") int size) {
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(medicineService.getAll(paging));
    }

    @GetMapping("/{id:[\\d]+}")
    public ResponseEntity<MedicineDto> getById(@PathVariable Long id) {
        return medicineService.findById(id)
                .map(medicine -> ResponseEntity.ok(modelMapper.map(medicine, MedicineDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicineDto> create(@Valid @RequestBody MedicineDto medicineDto) {
        return ResponseEntity.ok(modelMapper.map(medicineService.save(medicineDto), MedicineDto.class));
    }

    @PutMapping("/{id:[\\d]+}")
    public ResponseEntity<MedicineDto> update(
            @PathVariable Long id,
            @Valid @RequestBody MedicineDto medicineDto) {

        return medicineService.update(id, medicineDto)
                .map(medicine -> ResponseEntity.ok(modelMapper.map(medicine, MedicineDto.class)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id:[\\d]+}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        medicineService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
