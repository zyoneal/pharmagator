package com.eleks.academy.pharmagator.entities;

<<<<<<< HEAD
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
>>>>>>> development

import com.eleks.academy.pharmagator.view.MedicineRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

<<<<<<< HEAD
import javax.persistence.*;

=======
>>>>>>> development
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "medicines")
public class Medicine {

    @Id
<<<<<<< HEAD
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    public Medicine of(MedicineRequest medicineRequest) {
        return new Medicine(this.id, medicineRequest.getTitle());
    }

=======
    private Long id;
    private String title;

>>>>>>> development
}

