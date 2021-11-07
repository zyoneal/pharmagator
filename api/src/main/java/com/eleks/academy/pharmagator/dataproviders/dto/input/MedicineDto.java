package com.eleks.academy.pharmagator.dataproviders.dto.input;

import com.eleks.academy.pharmagator.entities.Medicine;
import lombok.Data;

@Data
public class MedicineDto {

    private String title;

    public static MedicineDto toDto(Medicine medicine) {
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setTitle(medicineDto.getTitle());
        return medicineDto;
    }

    public static Medicine toEntity(MedicineDto medicineDto) {
        Medicine medicine = new Medicine();
        medicine.setTitle(medicineDto.getTitle());
        return medicine;
    }

}
