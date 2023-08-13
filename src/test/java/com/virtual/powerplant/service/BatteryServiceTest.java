package com.virtual.powerplant.service;

import com.virtual.powerplant.dto.BatteryRequest;
import com.virtual.powerplant.entity.Battery;
import com.virtual.powerplant.exception.BusinessException;
import com.virtual.powerplant.repository.BatteryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BatteryServiceTest {

    @Mock
    private BatteryRepository batteryRepository;

    @InjectMocks
    private BatteryService batteryService;

    @Test
    void testSave() {
        var batteryRequests = List.of(
                new BatteryRequest("Battery 1", "6107", 1000),
                new BatteryRequest("Battery 2", "6110", 1500)
        );

        var batteryEntities = batteryRequests.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());

        when(batteryRepository.saveAll(anyList())).thenReturn(batteryEntities);

        var savedBatteries = batteryService.saveAll(batteryRequests);

        assertNotNull(savedBatteries);
        assertEquals(batteryRequests.size(), savedBatteries.size());
        verify(batteryRepository, times(1)).saveAll(anyList());
    }

    private Battery mapToEntity(BatteryRequest request) {
        var battery = new Battery();
        BeanUtils.copyProperties(request, battery);
        return battery;
    }

    @Test
    void testGetBatteriesByPostCodeRange() throws BusinessException {
        var postcodeRange = "6107-6110";
        var startPostCode = "6107";
        var endPostCode = "6110";

        var batteriesInRange = List.of(
                new Battery("Battery 1", startPostCode, 1000),
                new Battery("Battery 2", endPostCode, 1500)
        );

        when(batteryRepository.findBatteriesByPostcodeRange(startPostCode, endPostCode)).thenReturn(batteriesInRange);

        var batteryResponse = batteryService.getBatteriesByPostCodeRange(postcodeRange);

        assertNotNull(batteryResponse);
        assertEquals(2, batteryResponse.getBatteryNames().size());
        assertEquals(2500, batteryResponse.getTotalWattCapacity());
        assertEquals(1250.0, batteryResponse.getAverageWattCapacity());
    }

    @Test
    void testGetBatteriesByInvalidPostCodeRange() {
        var invalidPostcodeRange = "invalid";
        assertThrows(BusinessException.class, () -> batteryService.getBatteriesByPostCodeRange(invalidPostcodeRange));
    }
}