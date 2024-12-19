// HouseholdRosterController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.HouseholdRoster;
import com.ecl.popcensus.service.HouseholdRosterService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/household-rosters")
@SecurityRequirement(name = "bearerAuth")
public class HouseholdRosterController {
    private final HouseholdRosterService rosterService;

    public HouseholdRosterController(HouseholdRosterService rosterService) {
        this.rosterService = rosterService;
    }

    @PostMapping
    public ResponseEntity<HouseholdRoster> createRoster(
        @Valid @RequestBody HouseholdRoster roster
    ) {
        return ResponseEntity.ok(rosterService.createRoster(roster));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<List<HouseholdRoster>> getAllRostersByHouseholdId(
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(rosterService.getAllRostersByHouseholdId(householdId));
    }

    @GetMapping("/household/{householdId}/person/{personId}")
    public ResponseEntity<HouseholdRoster> getRosterByHouseholdIdAndPersonId(
        @PathVariable Long householdId,
        @PathVariable Integer personId
    ) {
        return ResponseEntity.ok(rosterService.getRosterByHouseholdIdAndPersonId(householdId, personId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<HouseholdRoster> getRosterByCensusFormId(
        @PathVariable Long censusFormId
    ) {
        return ResponseEntity.ok(rosterService.getRosterByCensusFormId(censusFormId));
    }

    @PutMapping("/household/{householdId}/person/{personId}")
    public ResponseEntity<HouseholdRoster> updateRoster(
        @PathVariable Long householdId,
        @PathVariable Integer personId,
        @Valid @RequestBody HouseholdRoster roster
    ) {
        return ResponseEntity.ok(rosterService.updateRoster(householdId, personId, roster));
    }

    @DeleteMapping("/household/{householdId}/person/{personId}")
    public ResponseEntity<Void> deleteRoster(
        @PathVariable Long householdId,
        @PathVariable Integer personId
    ) {
        rosterService.deleteRoster(householdId, personId);
        return ResponseEntity.ok().build();
    }
}
