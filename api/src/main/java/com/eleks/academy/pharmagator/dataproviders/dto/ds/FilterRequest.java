package com.eleks.academy.pharmagator.dataproviders.dto.ds;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterRequest {

    Long page;
    Long per;

}