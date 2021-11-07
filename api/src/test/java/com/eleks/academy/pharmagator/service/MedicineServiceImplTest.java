package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dataproviders.dto.input.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.services.MedicineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
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
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceImplTest {

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private MedicineServiceImpl medicineService;

    private Medicine testMedicine;

    private MedicineDto testMedicineDto;

    @BeforeEach
    public void init() {
        this.testMedicine = new Medicine(20211103L,"title");
        testMedicineDto = new MedicineDto();
        testMedicineDto.setTitle("testMedicineDto");
    }

    @Test
    public void test_findAll_ok() {
        when(this.medicineRepository.findAll())
                .thenReturn(LongStream.range(1L, 100L)
                .mapToObj(i -> new Medicine(i, String.format("title %d", i)))
                .collect(Collectors.toList())
        );
        final var medicines = this.medicineService.findAll();
        assertThat(medicines).isNotEmpty().matches(list -> list.size() == 99);
    }

    @Test
    public void test_repoReturnsEmptyList_ok() {

        when(this.medicineRepository.findAll())
                .thenReturn(List.of());

        final var medicines = this.medicineService.findAll();
        assertThat(medicines).isEmpty();
    }

    @Test
    public void test_findById_ok() {
        final Long id = 5L;
        when(this.medicineRepository.findById(id))
                .thenReturn(Optional.of(new Medicine(id, "")));
        final var medicine = this.medicineService.findById(id);
        assertThat(medicine).isNotEmpty();
        assertEquals(id, medicine.get().getId());
    }

    @Test
    public void test_findByIdOptionalEmpty_empty() {
        final Long id = 5L;
        when(this.medicineRepository.findById(id))
                .thenReturn(Optional.empty());
        final var medicine = this.medicineService.findById(id);
        assertThat(medicine).isEmpty();
    }

    @Test
    public void test_deleteById_ok() {
        this.medicineService.delete(this.testMedicine.getId());
        verify(this.medicineRepository, times(1)).deleteById(this.testMedicine.getId());
    }

    @Test
    public void test_deleteById_bad() {
        this.medicineService.delete(anyLong());
        verify(this.medicineRepository, times(0)).deleteById(this.testMedicine.getId());
    }

    @Test
    public void test_save_ok() {
        Medicine medicine = MedicineDto.toEntity(this.testMedicineDto);
        when(this.medicineRepository.save(medicine)).thenReturn(medicine);
        this.medicineService.save(this.testMedicineDto);
        verify(this.medicineRepository, times(1)).save(Mockito.any(Medicine.class));
    }

    @Test
    public void test_update_ok() {
        when(this.medicineRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(this.testMedicine));
        when(this.medicineRepository.save(Mockito.any(Medicine.class))).thenReturn(this.testMedicine);
        MedicineDto dto = MedicineDto.toDto(this.testMedicine);
        final var medicine = this.medicineService.update(Mockito.anyLong(), dto);
        verify(this.medicineRepository, times(1)).findById(Mockito.anyLong());
        verify(this.medicineRepository, times(1)).save(Mockito.any(Medicine.class));
    }

}
