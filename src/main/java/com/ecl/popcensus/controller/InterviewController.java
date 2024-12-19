// InterviewController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.Interview;
import com.ecl.popcensus.service.InterviewService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/interviews")
@SecurityRequirement(name = "bearerAuth")
public class InterviewController {
    private final InterviewService interviewService;

    public InterviewController(InterviewService interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping
    public ResponseEntity<Interview> createInterview(@Valid @RequestBody Interview interview) {
        return ResponseEntity.ok(interviewService.createInterview(interview));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<Interview> getByHouseholdId(@PathVariable Long householdId) {
        return ResponseEntity.ok(interviewService.getInterviewByHouseholdId(householdId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<Interview> getByCensusFormId(@PathVariable Long censusFormId) {
        return ResponseEntity.ok(interviewService.getInterviewByCensusFormId(censusFormId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Interview> updateInterview(
        @PathVariable Long id,
        @Valid @RequestBody Interview interview
    ) {
        return ResponseEntity.ok(interviewService.updateInterview(id, interview));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<Interview> completeInterview(@PathVariable Long id) {
        return ResponseEntity.ok(interviewService.completeInterview(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.ok().build();
    }
}
