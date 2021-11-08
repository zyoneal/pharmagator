package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.converters.PharmacyConverter;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.exceptions.PharmacyAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.PharmacyNotFoundException;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.view.requests.PharmacyRequest;
import com.eleks.academy.pharmagator.view.responses.PharmacyResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PharmacyServiceImpl implements PharmacyService {

    private final PharmacyRepository pharmacyRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PharmacyResponse> findAll() throws PharmacyNotFoundException {
        List<PharmacyResponse> pharmacies = pharmacyRepository.findAll()
                .stream().map(PharmacyResponse::of).collect(Collectors.toList());
        if (pharmacies.isEmpty()) {
            throw new PharmacyNotFoundException("pharmacy list is empty");
        }
        return pharmacies;
    }

    @Override
    public PharmacyResponse findById(Long id) {
        return PharmacyResponse.of(pharmacyRepository.findById(id).get());
    }

    @Override
    public PharmacyResponse create(PharmacyRequest pharmacyRequest) throws PharmacyAlreadyExistException {
        if (pharmacyRepository.findByName(pharmacyRequest.getName()) != null) {
            throw new PharmacyAlreadyExistException("A pharmacy with this name already exists");
        }
        return PharmacyResponse.of(pharmacyRepository.save(PharmacyConverter.of(pharmacyRequest)));
    }

    @Override
    public Optional<Pharmacy> update(Long id, PharmacyRequest pharmacyRequest) {
        return pharmacyRepository.findById(id)
                .map(ph -> {
                    Pharmacy pharmacy = PharmacyConverter.of(pharmacyRequest);
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
