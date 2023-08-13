package com.virtual.powerplant.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryRequest {

    @NotBlank(message = "Battery name is compulsory")
    private String name;

    @NotBlank(message = "Battery postcode is compulsory")
    private String postcode;

    @NotNull(message = "Battery capacity cannot be null")
    private Integer capacity;
}
