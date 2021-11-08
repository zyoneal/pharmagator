package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exceptions.PharmacyAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.PharmacyNotFoundException;
import com.eleks.academy.pharmagator.view.requests.PharmacyRequest;
import com.eleks.academy.pharmagator.view.responses.PharmacyResponse;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {

    List<PharmacyResponse> findAll() throws PharmacyNotFoundException;

    PharmacyResponse findById(Long id);

    PharmacyResponse create(PharmacyRequest pharmacyDto) throws PharmacyAlreadyExistException;

    Optional<Pharmacy> update(Long id, PharmacyRequest pharmacyDto);

    Long deleteById(Long id);

}
