package com.eleks.academy.pharmagator.dataproviders.dto.ds;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DSMedicineDto {

    String id;
    String name;
    BigDecimal price;
    String manufacturer;

}