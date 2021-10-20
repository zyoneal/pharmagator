package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PharmacyRepository;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.view.PharmacyRequest;
import com.eleks.academy.pharmagator.view.PharmacyResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyService {

    private PharmacyRepository pharmacyRepository;

    public List<PharmacyResponse> getAllPharmacies() {
        return this.pharmacyRepository.findAll()
                .stream()
                .map(PharmacyResponse::of)
                .collect(Collectors.toList());
    }

    public ResponseEntity<PharmacyResponse> getPharmacy(Long pharmacyId) {
        return this.pharmacyRepository.findById(pharmacyId)
                .map(PharmacyResponse::of)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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
        return this.pharmacyRepository.findAll().stream().filter(pharmacy -> pharmacy.getId() % 2 == 0).collect(Collectors.toList());
    }

}
