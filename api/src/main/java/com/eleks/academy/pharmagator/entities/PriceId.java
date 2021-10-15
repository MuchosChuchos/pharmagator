package com.eleks.academy.pharmagator.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceId implements Serializable {
    long pharmacyId;
    long medicineId;

    public PriceId() {

    }

    public PriceId(Long pharmacyId, Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
    }
}
