package com.eleks.academy.pharmagator.entities;

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
