package com.eleks.academy.pharmagator.dataproviders.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MedicineDto {

    @NotNull
    private String title;

}
