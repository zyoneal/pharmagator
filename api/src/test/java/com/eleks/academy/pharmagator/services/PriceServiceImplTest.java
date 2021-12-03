package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PriceDto;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
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

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class PriceServiceImplTest {

    @Mock
    private PriceRepository repository;

    @InjectMocks
    private PriceServiceImpl service;

    private PriceDto priceDto;

    private Price testPrice;

    private List<Price> priceList;

    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void init() {
        testPrice = new Price(1L, 2L, BigDecimal.TEN, "externalId", Instant.now());

        priceDto = new PriceDto();
        priceDto.setPrice(BigDecimal.ONE);
        priceDto.setExternalId("testExternalId");

        priceList = new ArrayList<>();
        priceList.add(new Price(1L, 2L, BigDecimal.valueOf(22.5), "ds", Instant.now()));
        priceList.add(new Price(2L, 43L, BigDecimal.valueOf(40.5), "apt", Instant.now()));
    }

    @Test
    void test_findAllPrices_ok() {
        when(this.repository.findAll()).thenReturn(priceList);
        List<Price> prices = service.findAll();
        verify(repository).findAll();
        assertThat(prices).isNotEmpty().matches(list -> list.size() == prices.size());
    }

    @Test
    void test_repoReturnsEmptyList_ok() {
        when(this.repository.findAll()).thenReturn(List.of());
        final var prices = this.service.findAll();
        assertThat(prices).isEmpty();
    }

    @Test
    void test_findById_ok() {
        final Long pharmacyId = 1L;
        final Long medicineId = 2L;
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        when(this.repository.findById(priceId)).thenReturn(Optional.ofNullable(this.testPrice));

        final var price = this.service.findById(pharmacyId, medicineId);
        assertThat(price).isNotEmpty();
        assertEquals(priceId.getMedicineId(), price.get().getMedicineId());
        assertEquals(priceId.getPharmacyId(), price.get().getPharmacyId());
    }

    @Test
    @Disabled
    void canUpdatePrice() {
        final Long pharmacyId = 1L;
        final Long medicineId = 2L;
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        service.update(pharmacyId, medicineId, priceDto);
        ArgumentCaptor<Price> priceArgumentCaptor = ArgumentCaptor.forClass(Price.class);
        verify(repository).findById(priceId);
        verify(repository).save(priceArgumentCaptor.capture());
    }

    @Test
    void test_finById_Empty() {
        final Long pharmacyId = 1L;
        final Long medicineId = 2L;
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        when(this.repository.findById(priceId))
                .thenReturn(Optional.empty());

        final var price = this.service.findById(pharmacyId, medicineId);
        assertThat(price).isEmpty();
    }

    @Test
    void deleteById_ok() {
        Long wrongPharmacyId = 1000002L;
        Long wrongMedicineId = 1000003L;
        PriceId wrongPriceId = new PriceId(wrongPharmacyId, wrongMedicineId);

        String exceptionMessage = String.format("No class %s entity with id PriceId(pharmacyId=%d, medicineId=%d) exists!", Price.class.getCanonicalName(), wrongPharmacyId, wrongMedicineId);

        doThrow(new EmptyResultDataAccessException(exceptionMessage, 0)).when(repository).deleteById(wrongPriceId);

        Exception exception = assertThrows(EmptyResultDataAccessException.class, () -> {
            repository.deleteById(wrongPriceId);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(exceptionMessage));
    }

}
