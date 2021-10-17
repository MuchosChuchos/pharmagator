package com.eleks.academy.pharmagator.repositories;

import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PriceRepository extends JpaRepository<Price, PriceId> {

    List<Price> findAllByMedicineId(Long medicineId);

    List<Price> findAllByPharmacyId(Long pharmacyId);

    List<Price> findAllByMedicineIdAndPharmacyId(Long medicineId, Long pharmacyId);

}
