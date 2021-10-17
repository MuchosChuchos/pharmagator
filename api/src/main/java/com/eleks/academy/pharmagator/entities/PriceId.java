package com.eleks.academy.pharmagator.entities;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PriceId implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pharmacyId;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long medicineId;

    public PriceId() {

    }

    public PriceId(Long pharmacyId, Long medicineId) {
        this.pharmacyId = pharmacyId;
        this.medicineId = medicineId;
    }
}
