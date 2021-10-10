package com.eleks.academy.pharmagator.dataproviders.dtoservices;

import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MappingService {

    @Autowired
    private MedicineRepository mr;

    public List<MedicineDto> getAllMedicinesPrice() {
        return mr.findAll()
                .stream()
                .map(this::convertDataIntoDTO)
                .collect(Collectors.toList());
    }

    private MedicineDto convertDataIntoDTO(Medicine medicine) {
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setTitle(medicine.getTitle());
        //-->crutch
        Price price = new Price();
        medicineDto.setPrice(price.getPrice());
        medicineDto.setExternalId(price.getExternalId());
        medicineDto.setUpdatedAt(price.getUpdatedAt());
        return medicineDto;
    }

    public static Medicine fromDtoToEntity(MedicineDto dto, Medicine medicine) {
        medicine.setTitle(dto.getTitle());
        return medicine;
    }

    public static Price fromDtoToEntity(MedicineDto dto, Price price) {
        price.setPrice(dto.getPrice());
        price.setExternalId(dto.getExternalId());
        price.setUpdatedAt(dto.getUpdatedAt());
        return price;
    }


}
