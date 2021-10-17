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
    public ResponseEntity<Medicine> createMedicine(@RequestBody Medicine medicine) {
        return new ResponseEntity<>(medicineRepository.save(medicine), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Medicine> updateMedicine(@PathVariable("id") Long id, @RequestBody Medicine medicine) {
        medicine.setId(id);
        return ResponseEntity.ok(medicineRepository.save(medicine));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMedicine(@PathVariable("id") Long id) {
        Medicine medicine = medicineRepository.findById(id).orElseThrow(() -> new InvalidIdentifierException(id));
        medicineRepository.delete(medicine);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
