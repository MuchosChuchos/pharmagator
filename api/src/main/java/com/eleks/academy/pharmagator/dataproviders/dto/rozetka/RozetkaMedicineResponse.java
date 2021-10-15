package com.eleks.academy.pharmagator.dataproviders.dto.rozetka;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RozetkaMedicineResponse {

    List<RozetkaMedicineDto> data;
}
