package com.eleks.academy.pharmagator.entities;


import com.eleks.academy.pharmagator.view.MedicineRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    public Medicine of(MedicineRequest medicineRequest) {
        return new Medicine(this.id, medicineRequest.getTitle());
    }

}

