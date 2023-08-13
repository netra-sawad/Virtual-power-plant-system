package com.virtual.powerplant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BatteryResponse {

    private int totalBattery;
    private int totalWattCapacity;
    private double averageWattCapacity;
    private List<String> batteryNames;
}
