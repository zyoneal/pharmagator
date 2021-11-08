package com.eleks.academy.pharmagator.dataproviders.converters;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.view.requests.PharmacyRequest;

public class PharmacyConverter {

    public static Pharmacy of(PharmacyRequest pharmacyRequest) {
        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(pharmacyRequest.getName());
        pharmacy.setMedicineLinkTemplate(pharmacyRequest.getMedicineLinkTemplate());
        return pharmacy;
    }

}
