// ICTController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.ICT;
import com.ecl.popcensus.service.ICTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/census-forms/{censusFormId}/households/{householdId}/ict")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class ICTController {
    private final ICTService ictService;

    public ICTController(ICTService ictService) {
        this.ictService = ictService;
    }

    @PostMapping
    public ResponseEntity<ICT> createICTInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody ICT ict
    ) {
        return ResponseEntity.ok(ictService.createICTInfo(censusFormId, householdId, ict));
    }

    @GetMapping
    public ResponseEntity<ICT> getICTInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId
    ) {
        return ResponseEntity.ok(ictService.getICTInfo(censusFormId, householdId));
    }

    @PutMapping
    public ResponseEntity<ICT> updateICTInfo(
        @PathVariable Long censusFormId,
        @PathVariable Long householdId,
        @Valid @RequestBody ICT ict
    ) {
        return ResponseEntity.ok(ictService.updateICTInfo(censusFormId, householdId, ict));
    }
}
