// Controllers
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.DwellingType;
import com.ecl.popcensus.service.DwellingTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/dwelling-types")
@SecurityRequirement(name = "bearerAuth")
public class DwellingTypeController {
    private final DwellingTypeService dwellingTypeService;

    public DwellingTypeController(DwellingTypeService dwellingTypeService) {
        this.dwellingTypeService = dwellingTypeService;
    }

    @PostMapping
    public ResponseEntity<DwellingType> createDwellingType(@Valid @RequestBody DwellingType dwellingType) {
        return ResponseEntity.ok(dwellingTypeService.createDwellingType(dwellingType));
    }

    @GetMapping
    public ResponseEntity<List<DwellingType>> getAllDwellingTypes() {
        return ResponseEntity.ok(dwellingTypeService.getAllDwellingTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DwellingType> getDwellingType(@PathVariable Long id) {
        return ResponseEntity.ok(dwellingTypeService.getDwellingTypeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DwellingType> updateDwellingType(
        @PathVariable Long id,
        @Valid @RequestBody DwellingType dwellingType
    ) {
        return ResponseEntity.ok(dwellingTypeService.updateDwellingType(id, dwellingType));
    }
}
