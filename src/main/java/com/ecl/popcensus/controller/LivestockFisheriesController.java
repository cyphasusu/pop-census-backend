// LivestockFisheriesController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.LivestockFisheries;
import com.ecl.popcensus.service.LivestockFisheriesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/census-forms/{censusFormId}/households/{householdId}/livestock-fisheries")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class LivestockFisheriesController {
    private final LivestockFisheriesService livestockFisheriesService;

    public LivestockFisheriesController(LivestockFisheriesService livestockFisheriesService) {
        this.livestockFisheriesService = livestockFisheriesService;
    }

    @PostMapping
    public ResponseEntity<LivestockFisheries> createLivestockFisheries(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody LivestockFisheries livestock
    ) {
        return ResponseEntity.ok(livestockFisheriesService.createLivestockFisheries(
            censusFormId, householdId, livestock));
    }

    @GetMapping
    public ResponseEntity<LivestockFisheries> getLivestockFisheries(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(livestockFisheriesService.getLivestockFisheries(
            censusFormId, householdId));
    }

    @PutMapping
    public ResponseEntity<LivestockFisheries> updateLivestockFisheries(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody LivestockFisheries livestock
    ) {
        return ResponseEntity.ok(livestockFisheriesService.updateLivestockFisheries(
            censusFormId, householdId, livestock));
    }
}

