package com.eleks.academy.pharmagator.entities;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "pharmacies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Pharmacy {
    @Id
    long id;
    String name;
    String medicineLinkTemplate;
}
