package com.eleks.academy.pharmagator.service;


import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.services.PharmacyServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PharmacyServiceTest {

    @Mock
    private PharmacyRepository pharmacyRepository;

    @InjectMocks
    private PharmacyServiceImpl pharmacyService;

    @Test
    public void test_ok() {
        when(this.pharmacyRepository.findAll()).
                thenReturn(LongStream.range(1L, 100L)
                        .mapToObj(i -> new Pharmacy(i, String.format("Name %d", i), ""))
                        .collect(Collectors.toList())
                );

        final var pharmacies = this.pharmacyService.findAll();
        assertThat(pharmacies).isNotEmpty().matches(list -> list.size() == 99);
    }

    @Test
    public void test_repoReturnsNull_NPE() {

        when(pharmacyRepository.findAll())
                .thenReturn(List.of());

        final var pharmacies = this.pharmacyService.findAll();
        assertThat(pharmacies).isEmpty();
    }

}