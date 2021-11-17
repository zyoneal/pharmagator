package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exceptions.PharmacyAlreadyExistsException;
import com.eleks.academy.pharmagator.exceptions.PharmacyNotFoundException;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;

    private final ModelMapper modelMapper;

    private static final String PHARMACY_WITH_NAME_ALREADY_EXISTS_MSG = "Pharmacy with name = %s already exists";
    private static final String PHARMACY_NOT_FOUND_ID_MSG = "Pharmacy with id = %s not found";

    @Override
    public List<Pharmacy> findAll() {
        return pharmacyRepository.findAll();
    }

    @Override
    public Optional<Pharmacy> findById(Long id) {
        getPharmacyById(id);
        return pharmacyRepository.findById(id);
    }

    @Override
    public Pharmacy save(PharmacyDto pharmacyDto) {
        String name = pharmacyDto.getName();
        Optional<Pharmacy> pharmacyByName = pharmacyRepository.findByName(name);

        if (pharmacyByName.isPresent()) {
            throw new PharmacyAlreadyExistsException(String.format(PHARMACY_WITH_NAME_ALREADY_EXISTS_MSG, name));
        }

        Pharmacy pharmacy = modelMapper.map(pharmacyDto, Pharmacy.class);
        return pharmacyRepository.save(pharmacy);
    }

    @Override
    public Optional<Pharmacy> update(Long id, PharmacyDto pharmacyDto) {
        return getPharmacyById(id)
                .map(ph -> {
                    Pharmacy pharmacy = modelMapper.map(pharmacyDto, Pharmacy.class);
                    pharmacy.setId(id);
                    return pharmacyRepository.save(pharmacy);
                });
    }

    @Override
    public void deleteById(Long id) {
        getPharmacyById(id);
        pharmacyRepository.deleteById(id);
    }

    private Optional<Pharmacy> getPharmacyById(Long id) {
        return Optional.ofNullable(pharmacyRepository.findById(id).orElseThrow(() -> new PharmacyNotFoundException(String.format(PHARMACY_NOT_FOUND_ID_MSG, id))));
    }

}
