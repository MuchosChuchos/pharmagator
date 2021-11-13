package com.eleks.academy.pharmagator.dataproviders.dto.input;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class PriceDto {

    @Min(value = 0)
    @NotNull
    private BigDecimal price;

    @NotNull
    private String externalId;

}
