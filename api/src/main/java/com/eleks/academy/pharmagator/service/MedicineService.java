package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.view.MedicineRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Optional<Medicine> getMedicine(Long medicineId) {
        return medicineRepository.findById(medicineId);
    }

    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    public void createOrUpdate(MedicineRequest medicineRequest) {
        Medicine medicineEntity = new Medicine();
        medicineEntity.of(medicineRequest);
        medicineRepository.save(medicineEntity);
    }

}
