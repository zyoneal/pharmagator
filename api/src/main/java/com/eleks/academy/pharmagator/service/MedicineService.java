package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.view.MedicineRequest;
import com.eleks.academy.pharmagator.view.MedicineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineService {

    private final MedicineRepository medicineRepository;

    public List<MedicineResponse> getAllMedicines() {
        return medicineRepository.findAll().stream().map(MedicineResponse::of).collect(Collectors.toList());
    }

    public ResponseEntity<MedicineResponse> getMedicine(Long medicineId) {
        return medicineRepository.findById(medicineId).map(MedicineResponse::of)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
