package com.eleks.academy.pharmagator.service;

import com.eleks.academy.pharmagator.dao.PriceRepository;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.view.PriceRequest;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;

    @InjectMocks
    private PriceService priceService;

    @Test
    public void test_getAllPrices_ok() {
        Mockito.when(this.priceRepository.findAll())
                .thenReturn(LongStream.range(1L, 100L)
                        .mapToObj(i -> new Price(i, i, BigDecimal.TEN, "mew", Instant.now()))
                        .collect(Collectors.toList()));

        final var prices = this.priceService.getAllPrices();
        assertThat(prices).matches(list -> list.size() == 99).isNotEmpty();
    }

    @Test
    public void test_getAllPrices_empty() {
        Mockito.when(this.priceRepository.findAll()).thenReturn(List.of());
        final var prices = this.priceService.getAllPrices();
        assertThat(prices).matches(list -> list.size() == 0).isEmpty();
    }

    @Test
    public void test_saveOrUpdatePrice_ok() {
        PriceRequest request = new PriceRequest(BigDecimal.TEN);
        Price price = new Price();
        Price priceToSaveOrUpdate = price.of(request);
        Mockito.when(this.priceRepository.save(priceToSaveOrUpdate)).thenReturn(priceToSaveOrUpdate);
        this.priceService.saveOrUpdate(request);
        Mockito.verify(this.priceRepository, Mockito.times(1)).save(any(Price.class));
    }

}
