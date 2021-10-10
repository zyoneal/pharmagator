package com.eleks.academy.pharmagator.dao;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PharmacyRepository extends JpaRepository<Pharmacy, Long> {

}
