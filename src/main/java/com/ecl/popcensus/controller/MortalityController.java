// MortalityController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.Mortality;
import com.ecl.popcensus.service.MortalityService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/census-forms/{censusFormId}/households/{householdId}/mortality")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class MortalityController {
    private final MortalityService mortalityService;

    public MortalityController(MortalityService mortalityService) {
        this.mortalityService = mortalityService;
    }

    @PostMapping
    public ResponseEntity<Mortality> createMortalityInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody Mortality mortality
    ) {
        return ResponseEntity.ok(mortalityService.createMortalityInfo(censusFormId, householdId, mortality));
    }

    @GetMapping
    public ResponseEntity<Mortality> getMortalityInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(mortalityService.getMortalityInfo(censusFormId, householdId));
    }

    @PutMapping
    public ResponseEntity<Mortality> updateMortalityInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody Mortality mortality
    ) {
        return ResponseEntity.ok(mortalityService.updateMortalityInfo(censusFormId, householdId, mortality));
    }
}
