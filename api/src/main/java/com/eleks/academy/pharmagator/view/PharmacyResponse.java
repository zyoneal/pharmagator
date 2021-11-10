package com.eleks.academy.pharmagator.view;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PharmacyResponse {

    private Long Id;

    private String name;

    public static PharmacyResponse of(Pharmacy pharmacy){
        return new PharmacyResponse(pharmacy.getId(), pharmacy.getName());
    }

}
