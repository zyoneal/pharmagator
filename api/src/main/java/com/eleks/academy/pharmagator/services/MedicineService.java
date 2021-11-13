package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface MedicineService {

    List<Medicine> findAll();

    Page<MedicineDto> getAll(Pageable pageable);

    Optional<Medicine> findById(Long id);

    Medicine save(MedicineDto medicineDto);

    Optional<Medicine> update(Long id, MedicineDto medicineDto);

    void delete(Long id);

}
