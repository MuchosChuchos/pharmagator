package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Pharmacy;
import com.eleks.academy.pharmagator.repositories.PharmacyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pharmacies")
public class PharmacyController {

    private final PharmacyRepository pharmacyRepository;

    @GetMapping
    public ResponseEntity<List<Pharmacy>> getAll() {
        List<Pharmacy> pharmacyList = pharmacyRepository.findAll();
        return ResponseEntity.ok(pharmacyList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pharmacy> getById(@PathVariable("id") Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).get();
        return ResponseEntity.ok(pharmacy);
    }

    @PostMapping
    public ResponseEntity<Pharmacy> createPharmacy(@RequestBody Pharmacy pharmacy) {
        return new ResponseEntity<>(pharmacyRepository.save(pharmacy), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Pharmacy> updatePharmacy(@PathVariable("id") Long id, @RequestBody Pharmacy pharmacy) {
        pharmacy.setId(id);
        return ResponseEntity.ok(pharmacyRepository.save(pharmacy));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Pharmacy pharmacy = pharmacyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid pharmacy id:" + id));
        pharmacyRepository.delete(pharmacy);

        return ResponseEntity.ok("Pharmacy was successfully deleted!");
    }
}
