package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.exceptions.MedicineAlreadyExistException;
import com.eleks.academy.pharmagator.exceptions.MedicineNotFoundException;
import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Medicine> findAll() throws MedicineNotFoundException {
        List<Medicine> medicines = medicineRepository.findAll();
        if (medicines.isEmpty()) {
            throw new MedicineNotFoundException("The drug list is empty");
        }
        return medicines;
    }

    @Override
    public Medicine findById(Long id){
        return medicineRepository.findById(id).get();
    }

    @Override
    public Medicine save(MedicineDto medicineDto) throws MedicineAlreadyExistException {
        if (medicineRepository.findByTitle(medicineDto.getTitle()) != null) {
            throw new MedicineAlreadyExistException("A drug with this name already exists");
        }
        return medicineRepository.save(modelMapper.map(medicineDto,Medicine.class));
    }

    @Override
    public Optional<Medicine> update(Long id, MedicineDto medicineDto) {
        return medicineRepository.findById(id)
                .map(source -> {
                    Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
                    medicine.setId(id);
                    return medicineRepository.save(medicine);
                });
    }

    public Long delete(Long id) {
        medicineRepository.deleteById(id);
        return id;
    }

}
