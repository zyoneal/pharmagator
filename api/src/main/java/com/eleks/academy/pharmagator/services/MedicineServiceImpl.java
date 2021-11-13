package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.exceptions.MedicineAlreadyExistsException;
import com.eleks.academy.pharmagator.exceptions.MedicineNotFoundException;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MedicineServiceImpl implements MedicineService {

    private static final String MEDICINE_WITH_TITLE_ALREADY_EXISTS_MSG = "Medicine with name = %s already exists";
    private static final String MEDICINE_NOT_FOUND_ID_MSG = "Medicine with id = %s not found";

    private final MedicineRepository medicineRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<Medicine> findAll() {
        return medicineRepository.findAll();
    }

    public Page<MedicineDto> getAll(Pageable pageable) {
        return medicineRepository.findAll(pageable)
                .map(medicine -> modelMapper.map(medicine, MedicineDto.class));
    }

    @Override
    public Optional<Medicine> findById(Long id) {
        getMedicineById(id);
        return medicineRepository.findById(id);
    }

    @Override
    public Medicine save(MedicineDto medicineDto) {
        String title = medicineDto.getTitle();
        Optional<Medicine> medicineByTitle = medicineRepository.findByTitle(title);

        if (medicineByTitle.isPresent()) {
            throw new MedicineAlreadyExistsException(String.format(MEDICINE_WITH_TITLE_ALREADY_EXISTS_MSG, title));
        }

        Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
        return medicineRepository.save(medicine);
    }

    @Override
    public Optional<Medicine> update(Long id, MedicineDto medicineDto) {
        return getMedicineById(id)
                .map(source -> {
                    Medicine medicine = modelMapper.map(medicineDto, Medicine.class);
                    medicine.setId(id);
                    return medicineRepository.save(medicine);
                });
    }

    public void delete(Long id) {
        getMedicineById(id);
        medicineRepository.deleteById(id);
    }

    private Optional<Medicine> getMedicineById(Long id) {
        return Optional.ofNullable(medicineRepository.findById(id).orElseThrow(() -> new MedicineNotFoundException(String.format(MEDICINE_NOT_FOUND_ID_MSG, id))));
    }

}
