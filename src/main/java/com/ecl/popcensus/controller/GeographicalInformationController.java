// GeographicalInformationController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.GeographicalInformation;
import com.ecl.popcensus.service.GeographicalInformationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/geographical-information")
@SecurityRequirement(name = "bearerAuth")
public class GeographicalInformationController {
    private final GeographicalInformationService geoService;

    public GeographicalInformationController(GeographicalInformationService geoService) {
        this.geoService = geoService;
    }

    @PostMapping
    public ResponseEntity<GeographicalInformation> createGeographicalInfo(
        @Valid @RequestBody GeographicalInformation geoInfo
    ) {
        return ResponseEntity.ok(geoService.createGeographicalInfo(geoInfo));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<GeographicalInformation> getByHouseholdId(
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(geoService.getGeographicalInfoByHouseholdId(householdId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<GeographicalInformation> getByCensusFormId(
        @PathVariable Long censusFormId
    ) {
        return ResponseEntity.ok(geoService.getGeographicalInfoByCensusFormId(censusFormId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GeographicalInformation> updateGeographicalInfo(
        @PathVariable Long id,
        @Valid @RequestBody GeographicalInformation geoInfo
    ) {
        return ResponseEntity.ok(geoService.updateGeographicalInfo(id, geoInfo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGeographicalInfo(@PathVariable Long id) {
        geoService.deleteGeographicalInfo(id);
        return ResponseEntity.ok().build();
    }
}
