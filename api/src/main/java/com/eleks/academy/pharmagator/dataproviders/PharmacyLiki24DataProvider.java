package com.eleks.academy.pharmagator.dataproviders;

import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dto.liki24.Liki24MedicineDto;
import com.eleks.academy.pharmagator.dataproviders.dto.liki24.Liki24MedicinesResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class PharmacyLiki24DataProvider implements DataProvider {

    private final WebClient liki24Client;

    public PharmacyLiki24DataProvider(@Qualifier("pharmacyLiki24WebClient") WebClient liki24Client) {
        this.liki24Client = liki24Client;
    }

    @Override
    public Stream<MedicineDto> loadData() {
        return fetchMedicineDto();
    }

    private Stream<MedicineDto> fetchMedicineDto() {
        long pageIndex = 1L;

        BiConsumer<Long, List<Liki24MedicinesResponse>> fillListByMedicineResponse = (page, medicinesResponseList1) -> {
            Liki24MedicinesResponse medicinesResponse = getLiki24MedicinesResponse(page);
            medicinesResponseList1.add(medicinesResponse);
        };

        Liki24MedicinesResponse liki24MedicinesResponse = getLiki24MedicinesResponse(pageIndex);

        if (liki24MedicinesResponse != null) {
            Long totalPages = liki24MedicinesResponse.getTotalPages();
            List<Liki24MedicinesResponse> medicinesResponseList = new ArrayList<>();

            LongStream.rangeClosed(pageIndex, totalPages)
                    .parallel()
                    .forEach(i -> fillListByMedicineResponse.accept(i, medicinesResponseList));

            return medicinesResponseList.stream()
                    .map(Liki24MedicinesResponse::getItems)
                    .flatMap(Collection::stream)
                    .map(this::mapToMedicineDto);
        }
        return Stream.of();
    }

    private Liki24MedicinesResponse getLiki24MedicinesResponse(Long page) {
        return liki24Client.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .build())
                .retrieve().bodyToMono(Liki24MedicinesResponse.class)
                .block();
    }

    private MedicineDto mapToMedicineDto(Liki24MedicineDto liki24MedicineDto) {
        return MedicineDto.builder()
                .externalId(liki24MedicineDto.getProductId().toString())
                .price(liki24MedicineDto.getPrice())
                .title(liki24MedicineDto.getName())
                .build();
    }

}
