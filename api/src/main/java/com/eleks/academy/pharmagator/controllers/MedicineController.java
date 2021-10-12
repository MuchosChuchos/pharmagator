package com.eleks.academy.pharmagator.controllers;

import com.eleks.academy.pharmagator.entities.Medicine;
import com.eleks.academy.pharmagator.exceptions.InvalidIdentifierException;
import com.eleks.academy.pharmagator.repositories.MedicineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/medicines")
public class MedicineController {

    private final MedicineRepository medicineRepository;

    @GetMapping
    public ResponseEntity<List<Medicine>> getAll() {
        return ResponseEntity.ok(medicineRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getById(@PathVariable("id") Long id) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new InvalidIdentifierException(id));
        return ResponseEntity.ok(medicine);
    }

    @PostMapping
    public ResponseEntity<Medicine> createPharmacy(@RequestBody Medicine medicine) {
        return new ResponseEntity<>(medicineRepository.save(medicine), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Medicine> updatePharmacy(@PathVariable("id") Long id, @RequestBody Medicine medicine) {
        medicine.setId(id);
        return ResponseEntity.ok(medicineRepository.save(medicine));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new InvalidIdentifierException(id));
        medicineRepository.delete(medicine);

        return ResponseEntity.ok("Medicine was successfully deleted!");
    }
}
