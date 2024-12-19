// Controllers
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.FloorType;
import com.ecl.popcensus.service.FloorTypeService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/floor-types")
@SecurityRequirement(name = "bearerAuth")
public class FloorTypeController {
    private final FloorTypeService floorTypeService;

    public FloorTypeController(FloorTypeService floorTypeService) {
        this.floorTypeService = floorTypeService;
    }

    @PostMapping
    public ResponseEntity<FloorType> createFloorType(@Valid @RequestBody FloorType floorType) {
        return ResponseEntity.ok(floorTypeService.createFloorType(floorType));
    }

    @GetMapping
    public ResponseEntity<List<FloorType>> getAllFloorTypes() {
        return ResponseEntity.ok(floorTypeService.getAllFloorTypes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FloorType> getFloorType(@PathVariable Long id) {
        return ResponseEntity.ok(floorTypeService.getFloorTypeById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FloorType> updateFloorType(
        @PathVariable Long id,
        @Valid @RequestBody FloorType floorType
    ) {
        return ResponseEntity.ok(floorTypeService.updateFloorType(id, floorType));
    }
}

