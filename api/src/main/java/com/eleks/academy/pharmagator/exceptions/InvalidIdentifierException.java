package com.eleks.academy.pharmagator.exceptions;

import com.eleks.academy.pharmagator.entities.PriceId;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class InvalidIdentifierException extends RuntimeException {

    public InvalidIdentifierException(Long id) {
        super("Invalid id: " + id);
    }

    public InvalidIdentifierException(PriceId id) {
        super("Invalid Price id: medicine_id: " + id.getMedicineId() + "; pharmacy_id: " + id.getPharmacyId());
    }

    public InvalidIdentifierException() {
        super("Invalid id.");
    }
}
