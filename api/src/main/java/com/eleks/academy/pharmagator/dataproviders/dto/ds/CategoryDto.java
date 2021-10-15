package com.eleks.academy.pharmagator.dataproviders.dto.ds;

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
public class CategoryDto {

    String name;
    String slug;
    List<CategoryDto> children;

    public CategoryDto setName(String name) {
        this.name = name;
        return this;
    }

}