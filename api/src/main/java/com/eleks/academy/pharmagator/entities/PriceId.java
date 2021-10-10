package com.eleks.academy.pharmagator.entities;

import lombok.Data;

import java.io.Serializable;

@Data
public class PriceId implements Serializable {
    private long pharmacyId;
    private long medicineId;
}