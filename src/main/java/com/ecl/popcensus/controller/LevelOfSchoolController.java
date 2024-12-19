// LevelOfSchoolController.java
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.LevelOfSchool;
import com.ecl.popcensus.service.LevelOfSchoolService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/levels-of-school")
@SecurityRequirement(name = "bearerAuth")
public class LevelOfSchoolController {
    private final LevelOfSchoolService levelOfSchoolService;

    public LevelOfSchoolController(LevelOfSchoolService levelOfSchoolService) {
        this.levelOfSchoolService = levelOfSchoolService;
    }

    @PostMapping
    public ResponseEntity<LevelOfSchool> createLevelOfSchool(
        @Valid @RequestBody LevelOfSchool levelOfSchool
    ) {
        return ResponseEntity.ok(levelOfSchoolService.createLevelOfSchool(levelOfSchool));
    }

    @GetMapping
    public ResponseEntity<List<LevelOfSchool>> getAllLevelsOfSchool() {
        return ResponseEntity.ok(levelOfSchoolService.getAllLevelsOfSchool());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LevelOfSchool> getLevelOfSchoolById(@PathVariable Long id) {
        return ResponseEntity.ok(levelOfSchoolService.getLevelOfSchoolById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<LevelOfSchool> getLevelOfSchoolByCode(@PathVariable String code) {
        return ResponseEntity.ok(levelOfSchoolService.getLevelOfSchoolByCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LevelOfSchool> updateLevelOfSchool(
        @PathVariable Long id,
        @Valid @RequestBody LevelOfSchool levelOfSchool
    ) {
        return ResponseEntity.ok(levelOfSchoolService.updateLevelOfSchool(id, levelOfSchool));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevelOfSchool(@PathVariable Long id) {
        levelOfSchoolService.deleteLevelOfSchool(id);
        return ResponseEntity.ok().build();
    }
}
