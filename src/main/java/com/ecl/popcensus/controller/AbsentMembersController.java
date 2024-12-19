// AbsentMembersController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.AbsentMembers;
import com.ecl.popcensus.service.AbsentMembersService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/absent-members")
@SecurityRequirement(name = "bearerAuth")
public class AbsentMembersController {
    private final AbsentMembersService absentMembersService;

    public AbsentMembersController(AbsentMembersService absentMembersService) {
        this.absentMembersService = absentMembersService;
    }

    @PostMapping
    public ResponseEntity<AbsentMembers> createAbsentMembers(
        @Valid @RequestBody AbsentMembers absentMembers
    ) {
        return ResponseEntity.ok(absentMembersService.createAbsentMembers(absentMembers));
    }

    @GetMapping("/household/{householdId}")
    public ResponseEntity<AbsentMembers> getByHouseholdId(
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(absentMembersService.getAbsentMembersByHouseholdId(householdId));
    }

    @GetMapping("/census-form/{censusFormId}")
    public ResponseEntity<AbsentMembers> getByCensusFormId(
        @PathVariable Long censusFormId
    ) {
        return ResponseEntity.ok(absentMembersService.getAbsentMembersByCensusFormId(censusFormId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AbsentMembers> updateAbsentMembers(
        @PathVariable Long id,
        @Valid @RequestBody AbsentMembers absentMembers
    ) {
        return ResponseEntity.ok(absentMembersService.updateAbsentMembers(id, absentMembers));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAbsentMembers(@PathVariable Long id) {
        absentMembersService.deleteAbsentMembers(id);
        return ResponseEntity.ok().build();
    }
}
