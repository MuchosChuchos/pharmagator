package com.eleks.academy.pharmagator.dataproviders.dto.input;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class PriceDto {

    @Min(value = 0)
    @NotBlank
    private BigDecimal price;

    @NotBlank
    private String externalId;

}
