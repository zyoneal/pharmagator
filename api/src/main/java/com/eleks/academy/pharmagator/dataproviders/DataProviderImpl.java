package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class DataProviderImpl implements DataProvider {
    @Override
    public Stream<MedicineDto> loadData() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(i -> buildDto(i));
    }

    private MedicineDto buildDto(int i) {
        MedicineDto medicineDto = new MedicineDto();
        medicineDto.setExternalId(String.valueOf(i));
        medicineDto.setTitle("title" + i);
        medicineDto.setPrice(BigDecimal.valueOf(Math.random()));
        medicineDto.setUpdatedAt(Instant.now());
        return medicineDto;
    }


}
