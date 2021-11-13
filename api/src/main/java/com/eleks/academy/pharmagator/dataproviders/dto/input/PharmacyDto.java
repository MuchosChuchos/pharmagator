package com.eleks.academy.pharmagator.dataproviders.dto.input;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PharmacyDto {

    @NotNull
    private String name;

    private String medicineLinkTemplate;

}
