package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.view.MedicineRequest;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicineServiceTest {

    @Mock
    private MedicineRepository medicineRepository;

    @InjectMocks
    private MedicineService medicineService;

    @Test
    public void test_getAllMedicines_ok() {
        Mockito.when(this.medicineRepository.findAll())
                .thenReturn(LongStream.range(1L, 100L)
                        .mapToObj(i -> new Medicine(i, "title" + i))
                        .collect(Collectors.toList()));

        final var medicines = this.medicineService.getAllMedicines();
        assertThat(medicines).isNotEmpty().matches(list -> list.size() == 99);
    }

    @Test
    public void test_getAllMedicines_empty() {
        Mockito.when(this.medicineRepository.findAll()).thenReturn(List.of());
        final var medicines = this.medicineService.getAllMedicines();
        assertThat(medicines).matches(list -> list.size() == 0).isEmpty();
    }

    @Test
    public void test_getMedicineById_ok() {
        Long id = 5L;
        Mockito.when(this.medicineRepository.findById(id)).thenReturn(java.util.Optional.of(new Medicine(5L, "")));
        final var resultMedicine = this.medicineService.getMedicine(id);
        assertThat(resultMedicine).isNotEmpty().map(medicine -> medicine.getId() == id);
    }

    @Test
    public void test_getMedicineById_repoReturnsNull_empty() {
        Long id = 5L;
        Mockito.when(this.medicineRepository.findById(id)).thenReturn(Optional.empty());
        final var medicine = this.medicineService.getMedicine(id);
        assertThat(medicine).isEmpty();
    }

    @Test
    public void test_deleteMedicineById_ok() {
        Medicine medicine = new Medicine(5L, "title");
        this.medicineService.deleteMedicine(medicine.getId());
        verify(this.medicineRepository, times(1)).deleteById(medicine.getId());
    }

    @Test
    public void test_deleteMedicineById_bad() {
        Medicine medicine = new Medicine(5L, "title");
        this.medicineService.deleteMedicine(anyLong());
        verify(this.medicineRepository, times(0)).deleteById(medicine.getId());
    }

    @Test
    public void test_createOrUpdateMedicine_ok() {
        MedicineRequest request = new MedicineRequest("title");
        Medicine medicine = new Medicine();
        Medicine medicineToSaveOrUpdate = medicine.of(request);
        Mockito.when(this.medicineRepository.save(medicineToSaveOrUpdate)).thenReturn(medicineToSaveOrUpdate);
        this.medicineService.createOrUpdate(request);
        Mockito.verify(this.medicineRepository, times(1)).save(Mockito.any(Medicine.class));
    }

}


