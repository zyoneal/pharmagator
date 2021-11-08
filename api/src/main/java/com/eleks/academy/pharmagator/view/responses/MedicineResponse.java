package com.eleks.academy.pharmagator.view.responses;

import com.eleks.academy.pharmagator.entities.Medicine;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicineResponse {

    private Long id;

    private String title;

    public static MedicineResponse of(Medicine medicine){
        return new MedicineResponse(medicine.getId(),medicine.getTitle());
    }

}
