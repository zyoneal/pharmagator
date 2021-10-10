package com.eleks.academy.pharmagator.controller;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dtoservices.MappingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MappingController {

    @Autowired
    private MappingService ms;

    @GetMapping("/medicinesPrice")
    @ResponseBody
    public ResponseEntity<List<MedicineDto>> getAllMedicinesPrice() {
        return ResponseEntity.ok(ms.getAllMedicinesPrice());
    }
}
