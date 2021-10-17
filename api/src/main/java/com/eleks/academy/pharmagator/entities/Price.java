package com.eleks.academy.pharmagator.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long pharmacyId;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long medicineId;
    BigDecimal price;
    String externalId;
    Instant updatedAt;
}
