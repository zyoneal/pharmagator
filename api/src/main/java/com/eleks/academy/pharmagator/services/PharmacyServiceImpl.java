package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exceptions.PharmacyAlreadyExistException;
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

    @Override
    public List<Pharmacy> findAll() throws PharmacyNotFoundException {
        List<Pharmacy> pharmacies = pharmacyRepository.findAll();
        if (pharmacies.isEmpty()) {
            throw new PharmacyNotFoundException("pharmacy list is empty");
        }
        return pharmacies;
    }

    @Override
    public Pharmacy findById(Long id) {
        return pharmacyRepository.findById(id).get();
    }

    @Override
    public Pharmacy save(PharmacyDto pharmacyDto) throws PharmacyAlreadyExistException {
        if (pharmacyRepository.findByName(pharmacyDto.getName()) != null) {
            throw new PharmacyAlreadyExistException("A pharmacy with this name already exists");
        }
        return pharmacyRepository.save(modelMapper.map(pharmacyDto, Pharmacy.class));
    }

    @Override
    public Optional<Pharmacy> update(Long id, PharmacyDto pharmacyDto) {
        return pharmacyRepository.findById(id)
                .map(ph -> {
                    Pharmacy pharmacy = modelMapper.map(pharmacyDto, Pharmacy.class);
                    pharmacy.setId(id);
                    return pharmacyRepository.save(pharmacy);
                });
    }

    @Override
    public Long deleteById(Long id) {
        pharmacyRepository.deleteById(id);
        return id;
    }

}
