package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PharmacyDto;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PharmacyServiceImplTest {

    @Mock
    private PharmacyRepository repository;

    @InjectMocks
    private PharmacyServiceImpl service;

    private PharmacyDto pharmacyDto;

    private List<Pharmacy> pharmacyList;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        pharmacyDto = new PharmacyDto();
        pharmacyDto.setName("Aptslav");
        pharmacyDto.setMedicineLinkTemplate("aptslav.com");

        pharmacyList = new ArrayList<>();
        pharmacyList.add(new Pharmacy(1L, "DS", "ds.com"));
        pharmacyList.add(new Pharmacy(2L, "Liki24", "liki24.com"));
        pharmacyList.add(new Pharmacy(3L, "ANC", "anc.com"));
    }

    @Test
    void test_findAll_ok() {
        when(this.repository.findAll()).thenReturn(pharmacyList);
        List<Pharmacy> pharmacies = this.service.findAll();
        verify(repository).findAll();
        assertThat(pharmacies).isNotEmpty().matches(list -> list.size() == pharmacyList.size());
    }

    @Test
    void test_repoReturnsEmptyList_ok() {
        when(this.repository.findAll()).thenReturn(List.of());
        final var pharmacies = this.service.findAll();
        assertThat(pharmacies).isEmpty();
    }

    @Test
    void test_findById_ok() {
        final var id = 10L;
        when(this.repository.findById(id)).thenReturn(Optional.of(new Pharmacy(id, "", "")));
        final var pharmacies = this.service.findById(id);
        assertThat(pharmacies).isNotEmpty();
        assertEquals(id, pharmacies.get().getId());
    }

    @Test
    void test_finById_Empty() {
        final var id = 10L;
        when(this.repository.findById(id)).thenReturn(Optional.empty());
        final var pharmacies = this.service.findById(id);
        assertThat(pharmacies).isEmpty();
    }

    @Test
    void canSavePharmacy() {
        service.save(pharmacyDto);
        ArgumentCaptor<Pharmacy> pharmacyArgumentCaptor = ArgumentCaptor.forClass(Pharmacy.class);
        verify(repository).save(pharmacyArgumentCaptor.capture());
    }

    @Test
    @Disabled
    void canUpdatePharmacy() {
        final var id = 10L;
        service.update(id, pharmacyDto);
        ArgumentCaptor<Pharmacy> pharmacyArgumentCaptor = ArgumentCaptor.forClass(Pharmacy.class);
        verify(repository).findById(id);
        verify(repository).save(pharmacyArgumentCaptor.capture());
    }

    @Test
    void deleteById_ok() {
        Long wrongPharmacyId = 1000002L;
        String exceptionMessage = String.format("No class %s entity with id %d exists!", Pharmacy.class.getCanonicalName(), wrongPharmacyId);

        doThrow(new EmptyResultDataAccessException(exceptionMessage, 0)).when(repository).deleteById(wrongPharmacyId);

        Exception exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(wrongPharmacyId);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptionMessage));
    }

}
