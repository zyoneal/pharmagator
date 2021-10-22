package com.eleks.academy.pharmagator.entities;

<<<<<<< HEAD
import lombok.Data;

import java.io.Serializable;

@Data
public class PriceId implements Serializable {

    private long pharmacyId;

    private long medicineId;

    public PriceId(Long pharmacyId, Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
    }

}
=======
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class PriceId implements Serializable {
    private long pharmacyId;
    private long medicineId;
}
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
