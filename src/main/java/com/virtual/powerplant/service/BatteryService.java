package com.virtual.powerplant.service;

import com.virtual.powerplant.dto.BatteryRequest;
import com.virtual.powerplant.dto.BatteryResponse;
import com.virtual.powerplant.entity.Battery;
import com.virtual.powerplant.exception.BusinessException;
import com.virtual.powerplant.repository.BatteryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BatteryService {

    private final BatteryRepository batteryRepository;

    public List<BatteryRequest> saveAll(List<BatteryRequest> batteries) {
        var batteryEntities = batteries.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
        batteryRepository.saveAll(batteryEntities);
        return batteries;
    }

    private Battery mapToEntity(BatteryRequest batteryRequest) {
        var battery = new Battery();
        BeanUtils.copyProperties(batteryRequest, battery);
        return battery;
    }

    public BatteryResponse getBatteriesByPostCodeRange(String postcodeRange) throws BusinessException {
        var range = postcodeRange.split("-"); // format: 01-10
        if (range.length < 2) {
            throw new BusinessException("Invalid postcode range");
        }

        var startPostCode = range[0].trim();
        var endPostCode = range[1].trim();
        var batteriesInRange = batteryRepository.findBatteriesByPostcodeRange(startPostCode, endPostCode);

        var totalWattCapacity = batteriesInRange.stream()
                .mapToInt(Battery::getCapacity)
                .sum();
        var averageWattCapacity = batteriesInRange.stream()
                .mapToInt(Battery::getCapacity)
                .average()
                .orElse(0.0);
        var roundedAverage = BigDecimal.valueOf(averageWattCapacity)
                .setScale(4, RoundingMode.HALF_UP);

        var batteryNames = batteriesInRange.stream()
                .map(Battery::getName)
                .toList();

        return new BatteryResponse(batteryNames.size(), totalWattCapacity, roundedAverage.doubleValue(), batteryNames);
    }

    public void deleteAll() {
        batteryRepository.deleteAll();
    }
}