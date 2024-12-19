
// CropTreeFarmingController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.CropTreeFarming;
import com.ecl.popcensus.service.CropTreeFarmingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/census-forms/{censusFormId}/households/{householdId}/crop-tree-farming")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class CropTreeFarmingController {
    private final CropTreeFarmingService cropTreeFarmingService;

    public CropTreeFarmingController(CropTreeFarmingService cropTreeFarmingService) {
        this.cropTreeFarmingService = cropTreeFarmingService;
    }

    @PostMapping
    public ResponseEntity<CropTreeFarming> createCropTreeFarming(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody CropTreeFarming farming
    ) {
        return ResponseEntity.ok(cropTreeFarmingService.createCropTreeFarming(
            censusFormId, householdId, farming));
    }

    @GetMapping
    public ResponseEntity<CropTreeFarming> getCropTreeFarming(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(cropTreeFarmingService.getCropTreeFarming(
            censusFormId, householdId));
    }

    @PutMapping
    public ResponseEntity<CropTreeFarming> updateCropTreeFarming(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody CropTreeFarming farming
    ) {
        return ResponseEntity.ok(cropTreeFarmingService.updateCropTreeFarming(
            censusFormId, householdId, farming));
    }
}
