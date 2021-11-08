package com.eleks.academy.pharmagator.services;

import com.eleks.academy.pharmagator.dataproviders.converters.PriceConverter;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import com.eleks.academy.pharmagator.view.requests.PriceRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final MedicineRepository medicineRepository;
    private final PharmacyRepository pharmacyRepository;

    @Override
    public List<Price> findAll() {
        return priceRepository.findAll();
    }

    @Override
    public Optional<Price> findById(Long pharmacyId, Long medicineId) {
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        return this.priceRepository.findById(priceId);
    }

    @Override
    public Price save(PriceRequest priceRequest) {
        Price price = PriceConverter.of(priceRequest);
        return priceRepository.save(price);
    }

    @Override
    public Optional<Price> update(Long pharmacyId, Long medicineId, PriceRequest priceRequest) {

        PriceId priceId = new PriceId(pharmacyId, medicineId);

        return this.priceRepository.findById(priceId)
                .map(source -> {
                    Price price = PriceConverter.of(priceRequest);
                    price.setPharmacyId(pharmacyId);
                    price.setMedicineId(medicineId);
                    return priceRepository.save(price);
                });
    }

    @Override
    public void deleteById(Long pharmacyId, Long medicineId) {
        PriceId priceId = new PriceId(pharmacyId, medicineId);
        priceRepository.deleteById(priceId);
    }

}
