package com.eleks.academy.pharmagator.schedule;


import com.eleks.academy.pharmagator.dao.MedicineRepository;
import com.eleks.academy.pharmagator.dao.PriceRepository;
import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {

    private final List<DataProvider> dataProviderList;

    private MedicineRepository medicineRepository;

    private PriceRepository priceRepository;

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        log.info("Scheduler started at {}", Instant.now());
        this.dataProviderList.stream().flatMap(DataProvider::loadData).forEach(this::storeToDatabase);
    }

    private void storeToDatabase(MedicineDto medicineDto) {
        log.info(medicineDto.getTitle() + " - " + medicineDto.getPrice());


        //TODO store to a data base
    }

}