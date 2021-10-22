package com.eleks.academy.pharmagator.dao;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.projections.PharmacyLight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

<<<<<<< HEAD:api/src/main/java/com/eleks/academy/pharmagator/dao/PharmacyRepository.java
=======
    @Query("SELECT pharmacy FROM Pharmacy pharmacy")
    List<PharmacyLight> findAllLight();

>>>>>>> development:api/src/main/java/com/eleks/academy/pharmagator/repositories/PharmacyRepository.java
}
