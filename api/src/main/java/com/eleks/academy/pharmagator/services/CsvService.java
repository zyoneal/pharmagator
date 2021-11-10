package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CsvService {

    private final MedicineRepository medicineRepository;

    private final ModelMapper modelMapper;

    public void save(MultipartFile file) {
        try {
            List<Medicine> medicines = CsvHelper.parse(file.getInputStream())
                    .stream()
                    .map(medicineDto -> this.modelMapper.map(medicineDto, Medicine.class))
                    .collect(Collectors.toList());
            this.medicineRepository.saveAll(medicines);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: " + e.getMessage());
        }
    }

}
