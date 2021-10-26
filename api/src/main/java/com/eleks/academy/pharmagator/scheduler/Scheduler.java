package com.eleks.academy.pharmagator.scheduler;

import com.eleks.academy.pharmagator.dataproviders.DataProvider;
import com.eleks.academy.pharmagator.dataproviders.dto.MedicineDto;
import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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
    private final PriceRepository priceRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private final ModelMapper modelMapper;

    @Scheduled(fixedDelay = 100, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        log.info("Scheduler started at {}", Instant.now());
        dataProviderList.stream()
                .flatMap(DataProvider::loadData)
                .forEach(this::storeToDatabase);
        log.info("Scheduler finished at {}", Instant.now());
    }

    private void storeToDatabase(MedicineDto dto) {
        Medicine medicine = modelMapper.map(dto, Medicine.class);
        Price price = modelMapper.map(dto, Price.class);

        Pharmacy pharmacy = new Pharmacy();
        pharmacy.setName(dto.getPharmacy());
        Pharmacy pharmacyFromDb = pharmacyRepository.findFirstByName(pharmacy.getName());
        if (pharmacyFromDb != null) {
            pharmacy.setId(pharmacyFromDb.getId());
        }
        pharmacyRepository.save(pharmacy);


        Medicine medFromDb = medicineRepository.findFirstByTitle(medicine.getTitle());
        if (medFromDb == null) {
            medicine.setId(null);
        } else {
            medicine.setId(medFromDb.getId());
        }
        medicineRepository.save(medicine);

        price.setMedicineId(medicine.getId());
        price.setPharmacyId(pharmacy.getId());
        price.setUpdatedAt(Instant.now());
        price.setExternalId(dto.getExternalId());
        priceRepository.save(price);
    }

}
