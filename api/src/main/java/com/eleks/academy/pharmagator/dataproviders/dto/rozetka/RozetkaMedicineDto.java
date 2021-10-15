package com.eleks.academy.pharmagator.dataproviders.dto.rozetka;

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
public class RozetkaMedicineDto {

    String id;
    String title;
    BigDecimal price;

}
