package com.eleks.academy.pharmagator.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
public class PriceId implements Serializable {
    private long pharmacyId;
    private long medicineId;

    public PriceId() {

    }

    public PriceId(Long pharmacyId, Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
    }
}
