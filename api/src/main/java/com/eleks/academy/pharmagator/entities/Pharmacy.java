package com.eleks.academy.pharmagator.entities;

<<<<<<< HEAD
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
=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "pharmacies")
public class Pharmacy {
    @Id
    private long id;
    private String name;
    private String medicineLinkTemplate;
}
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
