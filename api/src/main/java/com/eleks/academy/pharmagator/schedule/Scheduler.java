package com.eleks.academy.pharmagator.schedule;


import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.dao.PriceRepository;
import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dtoservices.MappingService;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.Price;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
@Component
public class Scheduler {

    private final DataProvider dataProvider;

    @Autowired
    MedicineRepository medicineRepository;

    @Autowired
    PriceRepository priceRepository;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        log.info("Scheduler started at {}", Instant.now());
        dataProvider.loadData().forEach(this::storeToDataBase);
    }


    private void storeToDataBase(MedicineDto medicineDto) {

        Medicine medicine = new Medicine();
        Price price = new Price();

        MappingService.fromDtoToEntity(medicineDto, medicine);
        MappingService.fromDtoToEntity(medicineDto, price);

        medicineRepository.save(medicine);
        priceRepository.save(price);

    }
}