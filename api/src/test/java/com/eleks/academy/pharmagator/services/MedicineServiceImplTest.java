package com.eleks.academy.pharmagator.services;


import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class MedicineServiceImplTest {

    @Mock
    private MedicineRepository repository;

    @InjectMocks
    private MedicineServiceImpl medicineService;

    MedicineDto medicineDto;

    @BeforeEach
    void init() {
        medicineDto = new MedicineDto();
        medicineDto.setTitle("Aspirin");
    }

    @Test
    void canFindAllMedicines() {
        repository.findAll();
        verify(repository).findAll();
    }

    @Test
    void test_findById_ok() {
        final var id = 10L;
        when(this.repository.findById(id)).thenReturn(Optional.of(new Medicine(id, "")));
        final var medicine = this.medicineService.findById(id);
        assertThat(medicine).isNotEmpty();
        assertEquals(id, medicine.get().getId());
    }

    @Test
    void test_finById_Empty() {
        final var id = 10L;
        when(this.repository.findById(id)).thenReturn(Optional.empty());
        final var medicine = this.medicineService.findById(id);
        assertThat(medicine).isEmpty();
    }

    @Test
    @Disabled
    void canSaveMedicine() {
        medicineService.save(medicineDto);
        ArgumentCaptor<Medicine> medicineArgumentCaptor = ArgumentCaptor.forClass(Medicine.class);
        verify(repository).save(medicineArgumentCaptor.capture());
    }

    @Test
    @Disabled
    void canUpdateMedicine() {
        final var id = 10L;
        medicineService.update(id,medicineDto);
        ArgumentCaptor<Medicine> medicineArgumentCaptor = ArgumentCaptor.forClass(Medicine.class);
        verify(repository).findById(Mockito.anyLong());
        verify(repository).save(medicineArgumentCaptor.capture());
    }

    @Test
    void deleteById_ok() {
        Long wrongMedicineId = 1000002L;
        String exceptionMessage = String.format("No class %s entity with id %d exists!", Medicine.class.getCanonicalName(), wrongMedicineId);

        doThrow(new EmptyResultDataAccessException(exceptionMessage, 0)).when(repository).deleteById(wrongMedicineId);

        Exception exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(wrongMedicineId);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptionMessage));
    }

}
