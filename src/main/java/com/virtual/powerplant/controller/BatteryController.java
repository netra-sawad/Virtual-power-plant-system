package com.virtual.powerplant.controller;

import com.virtual.powerplant.dto.ApiResponse;
import com.virtual.powerplant.exception.BusinessException;
import com.virtual.powerplant.service.BatteryService;
import com.virtual.powerplant.util.ValidateRequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/batteries")
@RequiredArgsConstructor
public class BatteryController {

    private final BatteryService batteryService;

    @PostMapping
    public ResponseEntity<ApiResponse<?>> addBatteries(@Valid @RequestBody ValidateRequestBody requestBody) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .status(true)
                        .message("Battery Added Successfully")
                        .data(batteryService.saveAll(requestBody.getBatteries()))
                        .build());
    }

    @GetMapping("{postcodeRange}")
    public ResponseEntity<ApiResponse<?>> getBatteriesInPostCodeRange(@PathVariable String postcodeRange) throws BusinessException {
        return ResponseEntity.ok(
                ApiResponse
                        .builder()
                        .status(true)
                        .message("Battery retrieved.")
                        .data(batteryService.getBatteriesByPostCodeRange(postcodeRange))
                        .build());
    }
}