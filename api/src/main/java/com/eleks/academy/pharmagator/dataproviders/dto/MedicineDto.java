package com.eleks.academy.pharmagator.dataproviders.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MedicineDto {
    String title;
    BigDecimal price;
    String externalId;
    String pharmacy;
}
