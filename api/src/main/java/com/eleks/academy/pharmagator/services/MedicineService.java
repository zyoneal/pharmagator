package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.exceptions.MedicineAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.MedicineNotFoundException;
import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    List<Medicine> findAll() throws MedicineNotFoundException;

    Medicine findById(Long id);

    Medicine save(MedicineDto medicineDto) throws MedicineAlreadyExistException;

    Optional<Medicine> update(Long id, MedicineDto medicineDto);

    Long delete(Long id);

}
