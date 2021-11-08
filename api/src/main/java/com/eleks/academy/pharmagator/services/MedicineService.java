package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.exceptions.MedicineAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.MedicineNotFoundException;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.view.requests.MedicineRequest;
import com.eleks.academy.pharmagator.view.responses.MedicineResponse;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    List<MedicineResponse> findAll() throws MedicineNotFoundException;

    MedicineResponse findById(Long id);

    MedicineResponse save(MedicineRequest medicineDto) throws MedicineAlreadyExistException;

    Optional<Medicine> update(Long id, MedicineRequest medicineDto);

    Long delete(Long id);

}
