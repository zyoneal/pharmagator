package com.eleks.academy.pharmagator.entities;

<<<<<<< HEAD
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
=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "medicines")
public class Medicine {
    @Id
    private long id;
    private String title;
}
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
