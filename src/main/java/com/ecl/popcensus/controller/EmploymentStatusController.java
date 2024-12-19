// Controllers
package com.ecl.popcensus.controller;

import com.ecl.popcensus.model.EmploymentStatus;
import com.ecl.popcensus.service.EmploymentStatusService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/employment-statuses")
@SecurityRequirement(name = "bearerAuth")
public class EmploymentStatusController {
    private final EmploymentStatusService service;

    public EmploymentStatusController(EmploymentStatusService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EmploymentStatus> create(@Valid @RequestBody EmploymentStatus status) {
        return ResponseEntity.ok(service.create(status));
    }

    @GetMapping
    public ResponseEntity<List<EmploymentStatus>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmploymentStatus> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<EmploymentStatus> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(service.getByCode(code));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmploymentStatus> update(
        @PathVariable Long id,
        @Valid @RequestBody EmploymentStatus status
    ) {
        return ResponseEntity.ok(service.update(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
