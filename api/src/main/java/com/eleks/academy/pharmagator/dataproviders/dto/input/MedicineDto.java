package com.eleks.academy.pharmagator.dataproviders.dto.input;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MedicineDto {

    @NotBlank
    private String title;

}
