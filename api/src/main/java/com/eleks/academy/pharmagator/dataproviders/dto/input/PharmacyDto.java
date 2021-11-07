package com.eleks.academy.pharmagator.dataproviders.dto.input;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import lombok.Data;

@Data
public class PharmacyDto {
    private String name;
    private String medicineLinkTemplate;

    public static PharmacyDto toDto(Pharmacy pharmacy) {
        PharmacyDto pharmacyDto = new PharmacyDto();
        pharmacyDto.setName(pharmacy.getName());
        pharmacyDto.setMedicineLinkTemplate(pharmacy.getMedicineLinkTemplate());
        return pharmacyDto;
    }

    public static Pharmacy toEntity(PharmacyDto pharmacyDto) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(pharmacyDto.getName());
        pharmacy.setMedicineLinkTemplate(pharmacyDto.getMedicineLinkTemplate());
        return pharmacy;
    }

}
