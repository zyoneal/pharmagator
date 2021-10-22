package com.eleks.academy.pharmagator.entities;

<<<<<<< HEAD
import com.eleks.academy.pharmagator.view.PriceRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prices")
@IdClass(PriceId.class)
public class Price {

    @Id
    private Long pharmacyId;

    @Id
    private Long medicineId;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private String externalId;

    @Column(nullable = false)
    private Instant updatedAt;

    public Price of(PriceRequest priceRequest) {
        return new Price(this.pharmacyId, this.medicineId, priceRequest.getPrice(), this.externalId, this.updatedAt);
    }

}
=======
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "prices")
@IdClass(PriceId.class)
public class Price {
    @Id
    private long pharmacyId;
    @Id
    private long medicineId;
    private BigDecimal price;
    private String externalId;
    private Instant updatedAt;
}
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
