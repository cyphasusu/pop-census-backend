// EmigrationController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.Emigration;
import com.ecl.popcensus.service.EmigrationService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/emigrations")
@SecurityRequirement(name = "bearerAuth")
public class EmigrationController {
    private final EmigrationService emigrationService;

    public EmigrationController(EmigrationService emigrationService) {
        this.emigrationService = emigrationService;
    }

    @PostMapping
    public ResponseEntity<Emigration> createEmigration(
        @Valid @RequestBody Emigration emigration
    ) {
        return ResponseEntity.ok(emigrationService.createEmigration(emigration));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<Emigration> getByHouseholdId(
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(emigrationService.getEmigrationByHouseholdId(householdId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<Emigration> getByCensusFormId(
        @PathVariable Long censusFormId
    ) {
        return ResponseEntity.ok(emigrationService.getEmigrationByCensusFormId(censusFormId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emigration> updateEmigration(
        @PathVariable Long id,
        @Valid @RequestBody Emigration emigration
    ) {
        return ResponseEntity.ok(emigrationService.updateEmigration(id, emigration));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmigration(@PathVariable Long id) {
        emigrationService.deleteEmigration(id);
        return ResponseEntity.ok().build();
    }
}
