package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PharmacyRepository;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.view.PharmacyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private PharmacyRepository pharmacyRepository;

    public List<Pharmacy> getAllPharmacies() {
        return this.pharmacyRepository.findAll();
    }

    public Optional<Pharmacy> getPharmacy(Long pharmacyId) {
        return this.pharmacyRepository.findById(pharmacyId);
    }

    public void deletePharmacy(Long pharmacyId) {
        this.pharmacyRepository.deleteById(pharmacyId);
    }

    public void createOrUpdate(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacyEntity = new Pharmacy();
        Pharmacy pharmacy = pharmacyEntity.of(pharmacyRequest);
        this.pharmacyRepository.save(pharmacy);
    }

    public List<Pharmacy> findAllEven(){
        return this.pharmacyRepository.findAll()
                .stream()
                .filter(pharmacy -> pharmacy.getId() % 2 == 0)
                .collect(Collectors.toList());
    }

}
