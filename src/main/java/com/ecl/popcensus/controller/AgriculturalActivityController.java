// AgriculturalActivityController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.AgriculturalActivity;
import com.ecl.popcensus.service.AgriculturalActivityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/census-forms/{censusFormId}/households/{householdId}/agricultural-activity")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class AgriculturalActivityController {
    private final AgriculturalActivityService agriculturalActivityService;

    public AgriculturalActivityController(AgriculturalActivityService agriculturalActivityService) {
        this.agriculturalActivityService = agriculturalActivityService;
    }

    @PostMapping
    public ResponseEntity<AgriculturalActivity> createAgriculturalActivity(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody AgriculturalActivity activity
    ) {
        return ResponseEntity.ok(agriculturalActivityService.createAgriculturalActivity(
            censusFormId, householdId, activity));
    }

    @GetMapping
    public ResponseEntity<AgriculturalActivity> getAgriculturalActivity(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(agriculturalActivityService.getAgriculturalActivity(
            censusFormId, householdId));
    }

    @PutMapping
    public ResponseEntity<AgriculturalActivity> updateAgriculturalActivity(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody AgriculturalActivity activity
    ) {
        return ResponseEntity.ok(agriculturalActivityService.updateAgriculturalActivity(
            censusFormId, householdId, activity));
    }
}
