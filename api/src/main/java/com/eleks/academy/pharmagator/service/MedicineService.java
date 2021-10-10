package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.entities.Medicine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    public List<Medicine> getAllMedicines() {
        return medicineRepository.findAll();
    }

    public Medicine getMedicine(Long medicineId) {
        return medicineRepository.findById(medicineId).get();
    }

    public void deleteMedicine(Long medicineId) {
        medicineRepository.deleteById(medicineId);
    }

    public Medicine saveOrUpdate(Medicine medicine) {
        return medicineRepository.save(medicine);
    }
}
