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
import java.util.stream.Stream;

@Service
@Qualifier("pharmacyLiki24DataProvider")
public class PharmacyLiki24DataProvider implements DataProvider {

    private final WebClient liki24Client;

    public PharmacyLiki24DataProvider(@Qualifier("pharmacyLiki24WebClient") WebClient liki24Client) {
        this.liki24Client = liki24Client;
    }

    @Override
    public Stream<MedicineDto> loadData() {
        return fetchMedicineDto();
    }

    private Stream<MedicineDto> fetchMedicineDto () {
        Long pageSize = 100L;
        Long pageIndex = 1L;

        Liki24MedicinesResponse liki24MedicinesResponse = liki24Client.get()
                .retrieve().bodyToMono(Liki24MedicinesResponse.class)
                .block();

        Long total;
        if (liki24MedicinesResponse != null) {
            total = liki24MedicinesResponse.getTotalPages();
            Long page = pageIndex;
            Long pageCount = total / pageSize;
            List<Liki24MedicinesResponse> medicinesResponseList = new ArrayList<>();

            while (page <= pageCount) {
                Liki24MedicinesResponse medicinesResponse = liki24Client.get()
                        .uri("?page=" + page)
                        .retrieve().bodyToMono(Liki24MedicinesResponse.class)
                        .block();
                medicinesResponseList.add(medicinesResponse);
                page++;
            }
            return medicinesResponseList.stream()
                    .map(Liki24MedicinesResponse :: getItems)
                    .flatMap(Collection:: stream)
                    .map(this::mapToMedicineDto);

        }
        return Stream.of();
    }

    private MedicineDto mapToMedicineDto(Liki24MedicineDto liki24MedicineDto) {
        return MedicineDto.builder()
                .externalId(liki24MedicineDto.getProductId().toString())
                .price(liki24MedicineDto.getPrice())
                .title(liki24MedicineDto.getName())
                .build();
    }

}
