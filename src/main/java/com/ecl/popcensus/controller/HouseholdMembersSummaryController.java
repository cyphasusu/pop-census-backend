// HouseholdMembersSummaryController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.HouseholdMembersSummary;
import com.ecl.popcensus.service.HouseholdMembersSummaryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/household-members-summaries")
@SecurityRequirement(name = "bearerAuth")
public class HouseholdMembersSummaryController {
    private final HouseholdMembersSummaryService summaryService;

    public HouseholdMembersSummaryController(HouseholdMembersSummaryService summaryService) {
        this.summaryService = summaryService;
    }

    @PostMapping
    public ResponseEntity<HouseholdMembersSummary> createSummary(
        @Valid @RequestBody HouseholdMembersSummary summary
    ) {
        return ResponseEntity.ok(summaryService.createSummary(summary));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<HouseholdMembersSummary> getByHouseholdId(
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(summaryService.getSummaryByHouseholdId(householdId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<HouseholdMembersSummary> getByCensusFormId(
        @PathVariable Long censusFormId
    ) {
        return ResponseEntity.ok(summaryService.getSummaryByCensusFormId(censusFormId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HouseholdMembersSummary> updateSummary(
        @PathVariable Long id,
        @Valid @RequestBody HouseholdMembersSummary summary
    ) {
        return ResponseEntity.ok(summaryService.updateSummary(id, summary));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSummary(@PathVariable Long id) {
        summaryService.deleteSummary(id);
        return ResponseEntity.ok().build();
    }
}
