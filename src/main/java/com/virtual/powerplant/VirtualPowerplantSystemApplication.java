package com.virtual.powerplant;

import com.virtual.powerplant.dto.BatteryRequest;
import com.virtual.powerplant.service.BatteryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaAuditing(dateTimeProviderRef = "utcDateTimeProvider")
@Slf4j
public class VirtualPowerplantSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VirtualPowerplantSystemApplication.class, args);
    }

    @Bean
    public DateTimeProvider utcDateTimeProvider() {
        return () -> Optional.of(LocalDateTime.now(ZoneOffset.UTC));
    }

    @Bean
    public CommandLineRunner dataInitializer(BatteryService batteryService) {
        return args -> {
//            batteryService.deleteAll();

            var batteryRequests = List.of(
                    new BatteryRequest("Cannington", "6107", 13500),
                    new BatteryRequest("Midland", "6057", 50500),
                    new BatteryRequest("Hay Street", "6000", 23500),
                    new BatteryRequest("Mount Adams", "6525", 12000),
                    new BatteryRequest("Koolan Island", "6733", 10000),
                    new BatteryRequest("Armadale", "6992", 25000),
                    new BatteryRequest("Kalamunda", "6076", 13500),
                    new BatteryRequest("Carmel", "6076", 36000),
                    new BatteryRequest("Bentley", "6102", 85000),
                    new BatteryRequest("Akunda Bay", "2084", 13500),
                    new BatteryRequest("University of Melbourne", "3010", 85000),
                    new BatteryRequest("Norfolk Island", "2899", 13500),
                    new BatteryRequest("Kent Town", "5067", 13500),
                    new BatteryRequest("Northgate Mc", "9464", 13500),
                    new BatteryRequest("Gold Coast Mc", "9729", 50000)
            );

            batteryService.saveAll(batteryRequests);
            log.info("Data saved successfully.");
        };
    }
}
