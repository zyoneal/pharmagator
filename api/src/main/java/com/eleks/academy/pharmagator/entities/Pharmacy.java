package com.eleks.academy.pharmagator.entities;

import com.eleks.academy.pharmagator.view.PharmacyRequest;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "pharmacies")
public class Pharmacy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String medicineLinkTemplate;

    public Pharmacy of(PharmacyRequest pharmacyRequest){
        return new Pharmacy(this.id,pharmacyRequest.getName(),pharmacyRequest.getMedicineLinkTemplate());
    }

}