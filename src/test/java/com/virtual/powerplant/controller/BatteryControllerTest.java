package com.virtual.powerplant.controller;

import com.virtual.powerplant.dto.BatteryRequest;
import com.virtual.powerplant.exception.BusinessException;
import com.virtual.powerplant.service.BatteryService;
import com.virtual.powerplant.util.ValidateRequestBody;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatusCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class BatteryControllerTest {

    @Mock
    private BatteryService batteryService;

    @InjectMocks
    private BatteryController batteryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBatteries() {
        var batteryRequests = new ArrayList<>(
                Arrays.asList(
                        new BatteryRequest("Battery", "6000", 10),
                        new BatteryRequest("Battery", "7000", 10)
                )
        );
        
        var validateRequestBody = ValidateRequestBody.builder().batteries(batteryRequests).build();
        var response = batteryController.addBatteries(validateRequestBody);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Battery Added Successfully", Objects.requireNonNull(response.getBody()).getMessage());
        verify(batteryService, times(1)).saveAll(batteryRequests);
    }

    @Test
    void testGetBatteriesInPostCodeRange() throws BusinessException {
        var postcodeRange = "6000-7000";
        var response = batteryController.getBatteriesInPostCodeRange(postcodeRange);
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertEquals("Battery retrieved.", Objects.requireNonNull(response.getBody()).getMessage());
        verify(batteryService, times(1)).getBatteriesByPostCodeRange(postcodeRange);
    }
}