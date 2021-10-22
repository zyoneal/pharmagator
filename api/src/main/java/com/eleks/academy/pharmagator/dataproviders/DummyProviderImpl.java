package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@Service
public class DummyProviderImpl implements DataProvider {
<<<<<<< HEAD

    @Override
    public Stream<MedicineDto> loadData() {
        return IntStream.rangeClosed(1, 100).mapToObj(this::buildDto);
=======
    @Override
    public Stream<MedicineDto> loadData() {
        return IntStream.rangeClosed(1, 100)
                .mapToObj(this::buildDto);
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
    }

    private MedicineDto buildDto(int i) {
        return MedicineDto.builder()
                .externalId(String.valueOf(i))
                .title("title" + i)
                .price(BigDecimal.valueOf(Math.random()))
                .build();
    }
<<<<<<< HEAD

=======
>>>>>>> f4389f55eda148a046470d1096abd5cb293353ae
}
