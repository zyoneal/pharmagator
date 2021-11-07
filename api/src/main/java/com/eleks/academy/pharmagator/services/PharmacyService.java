package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exceptions.PharmacyAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.PharmacyNotFoundException;

import java.util.List;
import java.util.Optional;

public interface PharmacyService {

    List<Pharmacy> findAll() throws PharmacyNotFoundException;

    Pharmacy findById(Long id);

    Pharmacy save(PharmacyDto pharmacyDto) throws PharmacyAlreadyExistException;

    Optional<Pharmacy> update(Long id, PharmacyDto pharmacyDto);

    Long deleteById(Long id);

}
