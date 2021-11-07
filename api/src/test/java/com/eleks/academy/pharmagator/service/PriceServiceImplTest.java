package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dataproviders.dto.input.PriceDto;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import com.eleks.academy.pharmagator.services.PriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceImplTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceServiceImpl priceService;

    private Price testPrice;

    private PriceDto testPriceDto;

    @BeforeEach
    public void init() {
        this.testPrice = new Price(1L, 2L, BigDecimal.TEN, "externalId", Instant.now());
        this.testPriceDto = new PriceDto();
        this.testPriceDto.setPrice(BigDecimal.ONE);
        this.testPriceDto.setExternalId("testExternalId");
    }

    @Test
    public void test_findAllPrices_ok() {
        Mockito.when(this.priceRepository.findAll())
                .thenReturn(LongStream.range(1L, 100L)
                        .mapToObj(i -> new Price(i, i, BigDecimal.TEN, "mew", Instant.now()))
                        .collect(Collectors.toList()));

        final var prices = this.priceService.findAll();
        assertThat(prices).isNotEmpty().matches(list -> list.size() == 99);
    }

    @Test
    public void test_findAllPrices_empty() {
        Mockito.when(this.priceRepository.findAll()).thenReturn(List.of());

        final var prices = this.priceService.findAll();
        assertThat(prices).matches(list -> list.size() == 0).isEmpty();
    }

    @Test
    public void test_findById_ok() {
        final Long pharmacyId = 1L;
        final Long medicineId = 2L;
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        when(this.priceRepository.findById(priceId)).thenReturn(Optional.ofNullable(this.testPrice));

        final var price = this.priceService.findById(pharmacyId, medicineId);
        assertThat(price).isNotEmpty();
        assertEquals(priceId.getMedicineId(), price.get().getMedicineId());
        assertEquals(priceId.getPharmacyId(), price.get().getPharmacyId());
    }

    @Test
    public void test_findByIdOptionalEmpty_empty() {
        final Long pharmacyId = 1L;
        final Long medicineId = 2L;
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        when(this.priceRepository.findById(priceId))
                .thenReturn(Optional.empty());

        final var pharmacy = this.priceService.findById(pharmacyId, medicineId);
        assertThat(pharmacy).isEmpty();
    }

    @Test
    public void test_save_ok() {
        when(this.priceRepository.save(any(Price.class))).thenReturn(this.testPrice);
        PriceDto dto = PriceDto.toDto(this.testPrice);
        final var price = this.priceService.save(dto);
        verify(this.priceRepository, times(1)).save(Mockito.any(Price.class));
    }

    @Test
    public void test_update_ok() {
        final Long pharmacyId = 1L;
        final Long medicineId = 2L;
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        when(this.priceRepository.findById(priceId)).thenReturn(Optional.ofNullable(this.testPrice));
        PriceDto dto = PriceDto.toDto(this.testPrice);
        final var price = this.priceService.update(pharmacyId, medicineId, dto);
        verify(this.priceRepository, times(1)).findById(priceId);
        verify(this.priceRepository, times(1)).save(any(Price.class));
    }

}
