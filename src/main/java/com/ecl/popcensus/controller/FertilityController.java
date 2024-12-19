// FertilityController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.Fertility;
import com.ecl.popcensus.service.FertilityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/census-forms/{censusFormId}/households/{householdId}/fertility")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class FertilityController {
    private final FertilityService fertilityService;

    public FertilityController(FertilityService fertilityService) {
        this.fertilityService = fertilityService;
    }

    @PostMapping
    public ResponseEntity<Fertility> createFertilityInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody Fertility fertility
    ) {
        return ResponseEntity.ok(fertilityService.createFertilityInfo(censusFormId, householdId, fertility));
    }

    @GetMapping
    public ResponseEntity<List<Fertility>> getFertilityByHousehold(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(fertilityService.getFertilityInfoByHousehold(censusFormId, householdId));
    }

    @PutMapping("/{fertilityId}")
    public ResponseEntity<Fertility> updateFertilityInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @PathVariable Long fertilityId,
        @Valid @RequestBody Fertility fertility
    ) {
        return ResponseEntity.ok(fertilityService.updateFertilityInfo(censusFormId, householdId, fertilityId, fertility));
    }
}
