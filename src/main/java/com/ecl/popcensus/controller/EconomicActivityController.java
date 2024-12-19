// EconomicActivityController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.EconomicActivity;
import com.ecl.popcensus.service.EconomicActivityService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/economic-activities")
@SecurityRequirement(name = "bearerAuth")
public class EconomicActivityController {
    private final EconomicActivityService economicActivityService;

    public EconomicActivityController(EconomicActivityService economicActivityService) {
        this.economicActivityService = economicActivityService;
    }

    @PostMapping
    public ResponseEntity<EconomicActivity> createEconomicActivity(
        @Valid @RequestBody EconomicActivity economicActivity
    ) {
        return ResponseEntity.ok(economicActivityService.createEconomicActivity(economicActivity));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<List<EconomicActivity>> getAllByHouseholdId(
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(economicActivityService.getAllEconomicActivitiesByHouseholdId(householdId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<List<EconomicActivity>> getAllByCensusFormId(
        @PathVariable Long censusFormId
    ) {
        return ResponseEntity.ok(economicActivityService.getAllEconomicActivitiesByCensusFormId(censusFormId));
    }

    @GetMapping("/household/{householdId}/person/{personId}")
    public ResponseEntity<EconomicActivity> getByPerson(
        @PathVariable Long householdId,
        @PathVariable Integer personId
    ) {
        return ResponseEntity.ok(economicActivityService.getEconomicActivityByPerson(householdId, personId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EconomicActivity> updateEconomicActivity(
        @PathVariable Long id,
        @Valid @RequestBody EconomicActivity economicActivity
    ) {
        return ResponseEntity.ok(economicActivityService.updateEconomicActivity(id, economicActivity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEconomicActivity(@PathVariable Long id) {
        economicActivityService.deleteEconomicActivity(id);
        return ResponseEntity.ok().build();
    }
}
