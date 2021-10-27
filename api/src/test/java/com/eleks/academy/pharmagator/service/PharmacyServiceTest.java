package com.eleks.academy.pharmagator.service;


import com.eleks.academy.pharmagator.dao.PharmacyRepository;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.view.PharmacyRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PharmacyServiceTest {

    @Mock
    private PharmacyRepository pharmacyRepository;

    @InjectMocks
    private PharmacyService pharmacyService;

    @Test
    public void test_ok() {
        when(this.pharmacyRepository.findAll()).
                thenReturn(LongStream.range(1L, 100L)
                        .mapToObj(i -> new Pharmacy(i, String.format("Name %d", i), ""))
                        .collect(Collectors.toList())
                );

        final var evenPharmacies = this.pharmacyService.findAllEven();
        assertThat(evenPharmacies).matches(list -> list.size() == 49).allMatch(record -> record.getId() % 2 == 0);
    }

    @Test
    public void test_repoReturnsNull_NPE() {
        when(this.pharmacyRepository.findAll())
                .thenReturn(null);

        assertThatExceptionOfType(NullPointerException.class)
                .isThrownBy(() -> this.pharmacyService.findAllEven());
    }

    @Test
    public void test_findPharmacyById_ok() {
        Long id = 3L;
        Pharmacy pharmacy = new Pharmacy(id, "", "");
        when(this.pharmacyRepository.findById(id))
                .thenReturn(java.util.Optional.of(pharmacy));

        final var returnPharmacy = this.pharmacyService.getPharmacy(id);
        assertThat(returnPharmacy).isNotEmpty().map(pharma -> pharma.getId() == id);
    }

    @Test
    public void test_findPharmacyById_empty() {
        Long id = 3L;
        when(this.pharmacyRepository.findById(id))
                .thenReturn(Optional.empty());
        final var pharmacy = this.pharmacyService.getPharmacy(id);
        assertThat(pharmacy).isEmpty();
    }

    @Test
    public void test_getAllPharmacies_ok() {
        Mockito.when(this.pharmacyRepository.findAll())
                .thenReturn(LongStream.range(1L, 100L)
                        .mapToObj(i -> new Pharmacy(i, "", ""))
                        .collect(Collectors.toList()));
        final var allPharmacies = this.pharmacyService.getAllPharmacies();
        assertThat(allPharmacies).isNotEmpty().matches(list -> list.size() == 99);
    }

    @Test
    public void test_getAllPharmacies_empty() {
        Mockito.when(this.pharmacyRepository.findAll())
                .thenReturn(List.of());
        final var pharmacies = this.pharmacyService.getAllPharmacies();
        assertThat(pharmacies).matches(list -> list.size() == 0).isEmpty();
    }

    @Test
    public void test_deletePharmacyById_ok() {
        Pharmacy pharmacy = new Pharmacy(5L, "pharma1", "localhos1");
        this.pharmacyService.deletePharmacy(pharmacy.getId());
        verify(this.pharmacyRepository, times(1)).deleteById(pharmacy.getId());
    }

    @Test
    public void test_deletePharmacyById_bad() {
        Pharmacy pharmacy = new Pharmacy(5L, "pharma1", "localhos1");
        this.pharmacyService.deletePharmacy(anyLong());
        verify(this.pharmacyRepository, times(0)).deleteById(pharmacy.getId());
    }

    @Test
    public void test_createOrUpdatePharmacy_ok() {
        PharmacyRequest request = new PharmacyRequest("name", "medicineLink");
        Pharmacy pharmacy = new Pharmacy();
        Pharmacy pharmacyToSaveOrUpdate = pharmacy.of(request);
        Mockito.when(this.pharmacyRepository.save(pharmacyToSaveOrUpdate)).thenReturn(pharmacyToSaveOrUpdate);
        this.pharmacyService.createOrUpdate(request);
        Mockito.verify(this.pharmacyRepository, times(1)).save(Mockito.any(Pharmacy.class));
    }

}