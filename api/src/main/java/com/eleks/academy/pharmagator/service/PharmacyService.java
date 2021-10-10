package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PharmacyRepository;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PharmacyService {
    @Autowired
    private PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getAllPharmacies() {
        return pharmacyRepository.findAll();
    }

    public Pharmacy getPharmacy(Long pharmacyId) {
        return pharmacyRepository.findById(pharmacyId).get();
    }

    public void deletePharmacy(Long pharmacyId) {
        pharmacyRepository.deleteById(pharmacyId);
    }

    public Pharmacy saveOrUpdate(Pharmacy pharmacy) {
        return pharmacyRepository.save(pharmacy);
    }
}
