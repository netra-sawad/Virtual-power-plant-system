package com.virtual.powerplant.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "battery")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Battery extends BaseEntity {

    private String name;

    private String postcode;

    private int capacity;


}