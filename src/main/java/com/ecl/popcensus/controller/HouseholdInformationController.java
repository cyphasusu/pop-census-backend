package com.ecl.popcensus.controller;

import com.ecl.popcensus.dto.requests.CensusFormRequest;
import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.requests.HouseholdInformationRequest;
import com.ecl.popcensus.dto.responses.CensusFormResponse;
import com.ecl.popcensus.dto.responses.CensusListResponse;
import com.ecl.popcensus.dto.responses.UserListResponse;
import com.ecl.popcensus.dto.responses.UserResponse;
import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.model.HouseholdInformation;
import com.ecl.popcensus.service.CensusFormService;
import com.ecl.popcensus.service.HouseholdInformationService;
import com.ecl.popcensus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/v1/census-forms/{formId}/households")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class HouseholdInformationController {
    private final HouseholdInformationService householdService;

    public HouseholdInformationController(HouseholdInformationService householdService) {
        this.householdService = householdService;
    }

    @PostMapping
    public ResponseEntity<HouseholdInformation> createHouseholdInfo(
        @PathVariable Long formId,
        @Valid @RequestBody HouseholdInformationRequest request
    ) {
        log.info("Received request for formId: " + formId);
        HouseholdInformation householdInfo = HouseholdInformation.fromRequest(request);
        return ResponseEntity.ok(householdService.createHouseholdInfo(formId, householdInfo));
    }

    @GetMapping
    public ResponseEntity<HouseholdInformation> getHouseholdByForm(@PathVariable Long formId) {
        return ResponseEntity.ok(householdService.getHouseholdInfoByFormId(formId));
    }

    @PutMapping("/{householdId}")
    public ResponseEntity<HouseholdInformation> updateHouseholdInfo(
        @PathVariable Long formId,
        @PathVariable Long householdId,
        @Valid @RequestBody HouseholdInformationRequest request
    ) {
        HouseholdInformation householdInfo = HouseholdInformation.fromRequest(request);
        return ResponseEntity.ok(householdService.updateHouseholdInfo(formId, householdId, householdInfo));
    }
}