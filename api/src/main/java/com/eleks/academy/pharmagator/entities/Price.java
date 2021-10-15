package com.eleks.academy.pharmagator.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;

@Entity
@Getter
@Setter
@Table(name = "prices")
@IdClass(PriceId.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Price {
    @Id
    long pharmacyId;
    @Id
    long medicineId;
    BigDecimal price;
    String externalId;
    Instant updatedAt;
}
