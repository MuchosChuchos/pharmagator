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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Component
public class Scheduler {

    private final DataProvider dataProvider;
    private final PriceRepository priceRepository;
    private final PharmacyRepository pharmacyRepository;
    private final MedicineRepository medicineRepository;
    private ModelMapper modelMapper;
    private long uniqueId = 0;
    private final Pharmacy pharmacy;

    {
        pharmacy = new Pharmacy();
        pharmacy.setId(999);
        pharmacy.setName("TOlya's med hub");
        pharmacy.setMedicineLinkTemplate("https://localhost:8080");
    }

    public Scheduler(DataProvider dataProvider, PriceRepository priceRepository, PharmacyRepository pharmacyRepository, MedicineRepository medicineRepository, ModelMapper modelMapper) {
        this.dataProvider = dataProvider;
        this.priceRepository = priceRepository;
        this.pharmacyRepository = pharmacyRepository;
        this.medicineRepository = medicineRepository;
        this.modelMapper = modelMapper;
    }

    @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.MINUTES)
    public void schedule() {
        log.info("Scheduler started at {}", Instant.now());
        dataProvider.loadData().forEach(this::storeToDatabase);
    }

    private void storeToDatabase(MedicineDto dto) {
        Medicine medicine = modelMapper.map(dto, Medicine.class);
        Price price = modelMapper.map(dto, Price.class);

        pharmacyRepository.save(pharmacy);

        findUniqueIdInMedicines();

        medicine.setId(uniqueId);
        medicineRepository.save(medicine);

        price.setMedicineId(uniqueId);
        price.setPharmacyId(pharmacy.getId());
        price.setUpdatedAt(Instant.now());
        priceRepository.save(price);
    }

    private void findUniqueIdInMedicines() {
        while (medicineRepository.existsById(uniqueId)) {
            ++uniqueId;
        }
    }
}
