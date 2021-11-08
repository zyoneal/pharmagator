package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.converters.MedicineConverter;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.exceptions.MedicineAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.MedicineNotFoundException;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.view.requests.MedicineRequest;
import com.eleks.academy.pharmagator.view.responses.MedicineResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;

    @Override
    public List<MedicineResponse> findAll() throws MedicineNotFoundException {
        List<MedicineResponse> medicines = medicineRepository.findAll().stream().map(MedicineResponse::of).collect(Collectors.toList());
        if (medicines.isEmpty()) {
            throw new MedicineNotFoundException("medicine list is empty");
        }
        return medicines;
    }

    @Override
    public MedicineResponse findById(Long id) {
        return MedicineResponse.of(medicineRepository.findById(id).get());
    }

    @Override
    public MedicineResponse save(MedicineRequest medicineRequest) throws MedicineAlreadyExistException {
        if (medicineRepository.findByTitle(medicineRequest.getTitle()) != null) {
            throw new MedicineAlreadyExistException("A medicine with this name already exists");
        }
        return MedicineResponse.of(medicineRepository.save(MedicineConverter.of(medicineRequest)));
    }

    @Override
    public Optional<Medicine> update(Long id, MedicineRequest medicineRequest) {
        return medicineRepository.findById(id)
                .map(source -> {
                    Medicine medicine = MedicineConverter.of(medicineRequest);
                    medicine.setId(id);
                    return medicineRepository.save(medicine);
                });
    }

    public Long delete(Long id) {
        medicineRepository.deleteById(id);
        return id;
    }

}
