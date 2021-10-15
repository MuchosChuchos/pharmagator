package com.eleks.academy.pharmagator.dataproviders.dto.rozetka;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RozetkaIdsResponseData {

    List<Long> ids;
    @JsonProperty("show_next")
    int showNext;

}
