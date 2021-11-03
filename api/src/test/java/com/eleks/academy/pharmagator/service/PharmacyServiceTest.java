package com.eleks.academy.pharmagator.service;


import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.services.PharmacyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PharmacyServiceTest {

    @Mock
    private PharmacyRepository pharmacyRepository;

    @InjectMocks
    private PharmacyServiceImpl pharmacyService;

    private ModelMapper modelMapper;

    private Pharmacy testPharmacy;

    private PharmacyDto testPharmacyDto;

    @BeforeEach
    public void init() {
        this.modelMapper = new ModelMapper();
        this.pharmacyService.setMapper(this.modelMapper);
        this.testPharmacy = new Pharmacy(20211103L, "testPharmacy", "localhost");
        this.testPharmacyDto = new PharmacyDto();
        this.testPharmacyDto.setName("testPharmacyDto");
        this.testPharmacyDto.setMedicineLinkTemplate("testMedicineLinkTemplate");
    }

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
    public void test_repoReturnsEmptyList_ok() {

        when(this.pharmacyRepository.findAll())
                .thenReturn(List.of());

        final var pharmacies = this.pharmacyService.findAll();
        assertThat(pharmacies).isEmpty();
    }

    @Test
    public void test_findById_ok() {
        final Long id = 5L;
        when(this.pharmacyRepository.findById(id))
                .thenReturn(Optional.of(new Pharmacy(id, "", "")));

        final var pharmacy = this.pharmacyService.findById(id);
        assertThat(pharmacy).isNotEmpty();
        assertEquals(id, pharmacy.get().getId());
    }

    @Test
    public void test_findByIdOptionalEmpty_empty() {
        final Long id = 5L;
        when(this.pharmacyRepository.findById(id))
                .thenReturn(Optional.empty());

        final var pharmacy = this.pharmacyService.findById(id);
        assertThat(pharmacy).isEmpty();
    }

    @Test
    public void test_deleteById_ok() {
        this.pharmacyService.deleteById(this.testPharmacy.getId());
        verify(this.pharmacyRepository, times(1)).deleteById(this.testPharmacy.getId());
    }

    @Test
    public void test_deleteById_bad() {
        this.pharmacyService.deleteById(anyLong());
        verify(this.pharmacyRepository, times(0)).deleteById(this.testPharmacy.getId());
    }

    @Test
    public void test_save_ok() {
        Pharmacy pharmacy = this.modelMapper.map(this.testPharmacyDto, Pharmacy.class);
        when(this.pharmacyRepository.save(pharmacy)).thenReturn(pharmacy);
        this.pharmacyService.save(this.testPharmacyDto);
        verify(this.pharmacyRepository, times(1)).save(Mockito.any(Pharmacy.class));
    }

    @Test
    public void test_update_ok() {
        when(pharmacyRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(testPharmacy));
        when(pharmacyRepository.save(Mockito.any(Pharmacy.class))).thenReturn(testPharmacy);
        PharmacyDto dto = this.modelMapper.map(testPharmacy, PharmacyDto.class);
        final var pharmacy = pharmacyService.update(Mockito.anyLong(), dto);
        verify(pharmacyRepository, times(1)).findById(Mockito.anyLong());
        verify(pharmacyRepository, times(1)).save(Mockito.any(Pharmacy.class));
    }


}
