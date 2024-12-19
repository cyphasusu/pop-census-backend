package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.OuterWall;
import com.ecl.popcensus.service.OuterWallService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/v1/outer-walls")
@SecurityRequirement(name = "bearerAuth")
public class OuterWallController {
    private final OuterWallService outerWallService;

    public OuterWallController(OuterWallService outerWallService) {
        this.outerWallService = outerWallService;
    }

    @PostMapping
    public ResponseEntity<OuterWall> createOuterWall(@Valid @RequestBody OuterWall outerWall) {
        return ResponseEntity.ok(outerWallService.createOuterWall(outerWall));
    }

    @GetMapping
    public ResponseEntity<List<OuterWall>> getAllOuterWalls() {
        return ResponseEntity.ok(outerWallService.getAllOuterWalls());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OuterWall> getOuterWall(@PathVariable Long id) {
        return ResponseEntity.ok(outerWallService.getOuterWallById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<OuterWall> updateOuterWall(
        @PathVariable Long id,
        @Valid @RequestBody OuterWall outerWall
    ) {
        return ResponseEntity.ok(outerWallService.updateOuterWall(id, outerWall));
    }
}
