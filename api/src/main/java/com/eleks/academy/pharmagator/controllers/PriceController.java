package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.entities.Price;
import com.eleks.academy.pharmagator.entities.PriceId;
import com.eleks.academy.pharmagator.exceptions.InvalidIdentifierException;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import com.eleks.academy.pharmagator.repositories.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/prices")
public class PriceController {

    private final PriceRepository priceRepository;

    @GetMapping
    public ResponseEntity<List<Price>> getAll() {
        return ResponseEntity.ok(priceRepository.findAll());
    }

    @GetMapping("/{pharmacy_id}/{medicine_id}")
    public ResponseEntity<Price> getById(@PathVariable("medicine_id") Long pharmacyId,
                                         @PathVariable("pharmacy_id") Long medicineId) {
        PriceId id = new PriceId(medicineId, pharmacyId);
        Price price = priceRepository.findById(id).orElseThrow(() -> new InvalidIdentifierException(id));
        return ResponseEntity.ok(price);
    }

    @PostMapping
    public ResponseEntity<Price> createPrice(@RequestBody Price price) {
        return new ResponseEntity<>(priceRepository.save(price), HttpStatus.CREATED);
    }

    @PutMapping("/update/{pharmacy_id}/{medicine_id}")
    public ResponseEntity<Price> updatePrice(
            @PathVariable("medicine_id") Long medicineId,
            @PathVariable("pharmacy_id") Long pharmacyId,
            @RequestBody Price price) {
        price.setPharmacyId(pharmacyId);
        price.setMedicineId(medicineId);
        return ResponseEntity.ok(priceRepository.save(price));
    }

    @GetMapping("/delete/{pharmacy_id}/{medicine_id}")
    public ResponseEntity<?> deletePrice(@PathVariable("medicine_id") Long pharmacyId,
                                        @PathVariable("pharmacy_id") Long medicineId) {
        PriceId id = new PriceId(medicineId, pharmacyId);
        Price price = priceRepository.findById(id).orElseThrow(() -> new InvalidIdentifierException(id));
        priceRepository.delete(price);

        return ResponseEntity.ok("Price was successfully deleted!");
    }
}
