package com.virtual.powerplant.util;

import com.virtual.powerplant.dto.BatteryRequest;
import jakarta.validation.Valid;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ValidateRequestBody {

    @Valid
    private List<BatteryRequest> batteries;
}
