package com.ecl.popcensus.controller;


import com.ecl.popcensus.dto.requests.CensusFormRequest;
import com.ecl.popcensus.dto.requests.RegisterUserRequest;
import com.ecl.popcensus.dto.responses.CensusFormResponse;
import com.ecl.popcensus.dto.responses.CensusListResponse;
import com.ecl.popcensus.dto.responses.UserListResponse;
import com.ecl.popcensus.dto.responses.UserResponse;
import com.ecl.popcensus.model.CensusForm;
import com.ecl.popcensus.service.CensusFormService;
import com.ecl.popcensus.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/forms")
@Slf4j
@SecurityRequirement(name = "bearerAuth")
public class CensusFormController {
    private final UserService userService;
    private final CensusFormService censusFormService;



    @Operation(summary = "Create census form", description = "Send a POST request to create a new census form")
    @PostMapping
    public CensusFormResponse createCensusForm(
            @Valid @RequestBody CensusFormRequest censusFormRequest
    ) {
        log.info("Received request to add census form");
        var newCensusForm = new CensusForm();
        newCensusForm.setCensusFormName(censusFormRequest.getCensusFormName());
        newCensusForm.setCensusFormLocation(censusFormRequest.getCensusFormLocation());
        newCensusForm.setCensusFormDesc(censusFormRequest.getCensusFormDesc());
        return censusFormService.createCensusForm(newCensusForm);
    }

    @Operation(summary = "Fetch all census forms", description = "Send a GET request to fetch all census form list")
    @GetMapping
    public CensusListResponse getCensusForms(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String censusFormName
    ) {
        log.info("Received request to get list of census forms");
        return this.censusFormService.getCensusForms(page, size, censusFormName);
    }


    @Operation(summary = "Fetch census forms by id", description = "Send a GET request to fetch census form details by id. Pass census form id as a path variable")
    @GetMapping(path = "{censusFormId}")
    public CensusFormResponse getCensusFormById(
            @PathVariable("censusFormId") Long censusFormId)
    {
        log.info("Received request to get census form by censusFormId : " + censusFormId);
        return this.censusFormService.getCensusFormById(censusFormId);
    }

    @Operation(summary = "Delete census form", description = "Send a DELETE request to remove census form. Pass census form id as a path variable")
    @DeleteMapping(path = "{censusFormId}")
    public CensusFormResponse deleteCensusFormById(
            @PathVariable("censusFormId") Long censusFormId)
    {
        log.info("Received request to delete census form by deleteCensusFormById : " + censusFormId);
        return this.censusFormService.deleteCensusFormById(censusFormId);
    }

    @Operation(summary = "Update census form", description = "Send a PUT request to update details of a census form. Pass census form id as a path variable")
    @PutMapping(path = "{censusFormId}")
    public CensusFormResponse updateCensusForm(
            @PathVariable("censusFormId") Long censusFormId,
            @Valid @RequestBody CensusFormRequest censusFormRequest) {
        log.info("Received request to update census form");
        var newCensusForm = new CensusForm();
        newCensusForm.setCensusFormName(censusFormRequest.getCensusFormName());
        newCensusForm.setCensusFormLocation(censusFormRequest.getCensusFormLocation());
        newCensusForm.setCensusFormDesc(censusFormRequest.getCensusFormDesc());
        return censusFormService.updateCensusForm(censusFormId, newCensusForm);
    }



}
