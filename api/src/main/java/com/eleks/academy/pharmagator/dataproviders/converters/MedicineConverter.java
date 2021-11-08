package com.eleks.academy.pharmagator.dataproviders.converters;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.view.requests.MedicineRequest;

public class MedicineConverter {

    public static Medicine of(MedicineRequest medicineRequest) {
        Medicine medicine = new Medicine();
        medicine.setTitle(medicineRequest.getTitle());
        return medicine;
    }

}
